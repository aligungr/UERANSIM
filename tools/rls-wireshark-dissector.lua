--[[
-- Dissector for Radio Link Simulation Protocol
-- (used by UERANSIM <https://github.com/aligungr/UERANSIM>).
--
-- CC0-1.0 2021 - Louis Royer (<https://github.com/louisroyer/RLS-wireshark-dissector>)
--]]

--[[
-- ProtoFields
--]]
local rls_protocol = Proto("RLS", "Radio Link Simulation Protocol")
local version_major = ProtoField.uint8("rls.version_major", "RLS Version Major", base.DEC)
local version_minor = ProtoField.uint8("rls.version_minor", "RLS Version Minor", base.DEC)
local version_patch = ProtoField.uint8("rls.version_patch", "RLS Version Patch", base.DEC)

local message_type_name = {
	[0] = "Reserved",
	[1] = "Cell Info Request",
	[2] = "Cell Info Response",
	[3] = "PDU Delivery"
}

local message_type = ProtoField.uint8("rls.message_type", "RLS Message Type", base.DEC, message_type_name)
local sti = ProtoField.uint64("rls.sti", "RLS Temporary Identifier", base.HEX)

-- For cell Info Request
local sim_pos_x = ProtoField.uint32("rls.sim_pos_x", "RLS Position X", base.DEC)
local sim_pos_y = ProtoField.uint32("rls.sim_pos_y", "RLS Position Y", base.DEC)
local sim_pos_z = ProtoField.uint32("rls.sim_pos_z", "RLS Position Z", base.DEC)

-- For cell Info Response
local mcc = ProtoField.uint16("rls.mcc", "MCC", base.DEC)
local mnc = ProtoField.uint16("rls.mnc", "MNC", base.DEC)
local long_mnc = ProtoField.bool("rls.long_mnc", "MNC is 3-digit", base.BOOL)
local nci = ProtoField.uint64("rls.nci", "NR Cell Identity", base.HEX)
local tac = ProtoField.uint32("rls.tac", "Tracking Area Code", base.DEC)
local dbm = ProtoField.int32("rls.dbm", "RLS Signal Strength (dBm)", base.DEC)
local gnb_name = ProtoField.string("rls.gnb_name", "gNB Name")
local link_ip = ProtoField.string("rls.link_ip", "gNB Link IP")

-- For PDU Delivery
local pdu_type_name = {
	[0] = "Reserved",
	[1] = "RRC",
	[2] = "Data"
}

local pdu_type = ProtoField.uint8("rls.pdu_type", "RLS PDU Type", base.DEC, pdu_type_name)

local rrc_channel_name = {
	[0] = "BCCH-BCH",
	[1] = "BCCH-DL-SCH",
	[2] = "DL-CCCH",
	[3] = "DL-DCCH",
	[4] = "PCCH",
	[5] = "UL-CCCH",
	[6] = "UL-CCCH1",
	[7] = "UL-DCCH",
}

local rrc_channel_dissector = {
	[0] = "nr-rrc.bcch.bch",
	[1] = "nr-rrc.bcch.dl.sch",
	[2] = "nr-rrc.dl.ccch",
	[3] = "nr-rrc.dl.dcch",
	[4] = "nr-rrc.pcch",
	[5] = "nr-rrc.ul.ccch",
	[6] = "nr-rrc.ul.ccch1",
	[7] = "nr-rrc.ul.dcch",
}

local rrc_channel = ProtoField.uint32("rls.rrc_channel", "RRC Channel", base.DEC, rrc_channel_name)
local session_id = ProtoField.uint32("rls.session_id", "PDU Session ID", base.DEC)

--[[
-- Dissector definition
--]]
rls_protocol.fields = {
	version_major, version_minor, version_patch, message_type, sti,
	sim_pos_x, sim_pos_y, sim_pos_z,
	mcc, mnc, long_mnc, nci, tac, dbm, gnb_name, link_ip,
	pdu_type, rrc_channel, session_id,
}

function rls_protocol.dissector(buffer, pinfo, tree)
	local length = buffer:len()
	if length == 0 then return end
	if buffer(0,1):uint() ~= 0x03 then return end

	pinfo.cols.protocol = rls_protocol.name
	local version_number = buffer(1,1):uint().."."
		..buffer(2,1):uint().."."
		..buffer(3,1):uint()
	local subtree = tree:add(rls_protocol, buffer(), "RLS Protocol Version "..version_number)
	local version = subtree:add(rls_protocol, buffer(2,3), "Version: "..version_number)
	version:add(version_major, buffer(1,1))
	version:add(version_minor, buffer(2,1))
	version:add(version_patch, buffer(3,1))
	subtree:add(message_type, buffer(4,1))
	local msg_type = buffer(4,1):uint()
	if msg_type <=0 or msg_type > 3 then return end
	pinfo.cols.info = message_type_name[msg_type]
	subtree:append_text(" - "..message_type_name[msg_type])
	subtree:add(sti, buffer(5,8))
	if msg_type == 1 then -- Cell Info Request
		subtree:add(sim_pos_x, buffer(13,4))
		subtree:add(sim_pos_y, buffer(17,4))
		subtree:add(sim_pos_z, buffer(21,4))
	elseif msg_type == 2 then -- Cell Info Response
		subtree:add(mcc, buffer(13,2))
		local mnc_tree = subtree:add(rls_protocol, buffer(15,3), "MNC: "..tostring(buffer(15,2):uint()))
		mnc_tree:add(mnc, buffer(15,2))
		mnc_tree:add(long_mnc, buffer(17,1))
		subtree:add(nci, buffer(18,8))
		subtree:add(tac, buffer(26,4))
		subtree:add(dbm, buffer(30,4))
		local gnb_name_len = buffer(34,4):uint()
		subtree:add(gnb_name, buffer(38,gnb_name_len))
		local link_ip_size = buffer(38+gnb_name_len,4):uint()
		subtree:add(link_ip, buffer(42+gnb_name_len,link_ip_size))
	elseif msg_type == 3 then -- PDU Delivery
		subtree:add(pdu_type, buffer(13,1))
		local pdu_type_value = buffer(13,1):uint()
		local pdu_len = buffer(14,4):uint()
		local payload_len = buffer(18+pdu_len,4):uint()
		if pdu_type_value == 1 then -- RRC
			subtree:add(rrc_channel, buffer(22+pdu_len,payload_len))
			local channel = buffer(22+pdu_len,payload_len):uint()
			Dissector.get(rrc_channel_dissector[channel]):call(buffer(18,pdu_len):tvb(), pinfo, tree)
		elseif pdu_type_value == 2 then -- DATA
			subtree:add(session_id, buffer(22+pdu_len,payload_len))
			Dissector.get("ip"):call(buffer(18,pdu_len):tvb(), pinfo, tree)
		end
	end
end

local udp_port = DissectorTable.get("udp.port")
udp_port:add(4997, rls_protocol)
