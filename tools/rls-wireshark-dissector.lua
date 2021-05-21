--[[
--
-- Dissector for Radio Link Simulation Protocol
-- (UERANSIM project <https://github.com/aligungr/UERANSIM>).
--
-- CC0-1.0 2021 - Louis Royer (<https://github.com/louisroyer/RLS-wireshark-dissector>)
--
--]]

local rlsProtocol = Proto("RLS", "UERANSIM Radio Link Simulation (RLS) Protocol")
local fields = rlsProtocol.fields

local msgTypeNames = {
    [0] = "[Reserved]",
    [1] = "[Reserved]",
    [2] = "[Reserved]",
    [3] = "[Reserved]",
    [4] = "Heartbeat",
    [5] = "Heartbeat ACK",
    [6] = "PDU Transmission",
    [7] = "PDU Transmission ACK",
}

local pduTypeNames = {
    [0] = "[Reserved]",
    [1] = "RRC",
    [2] = "Data"
}

local rrcMsgTypeNames = {
    [0] = "BCCH-BCH",
    [1] = "BCCH-DL-SCH",
    [2] = "DL-CCCH",
    [3] = "DL-DCCH",
    [4] = "PCCH",
    [5] = "UL-CCCH",
    [6] = "UL-CCCH1",
    [7] = "UL-DCCH",
}

local nrRrcDissectors = {
    [0] = "nr-rrc.bcch.bch",
    [1] = "nr-rrc.bcch.dl.sch",
    [2] = "nr-rrc.dl.ccch",
    [3] = "nr-rrc.dl.dcch",
    [4] = "nr-rrc.pcch",
    [5] = "nr-rrc.ul.ccch",
    [6] = "nr-rrc.ul.ccch1",
    [7] = "nr-rrc.ul.dcch",
}

fields.Version = ProtoField.string("rls.version", "Version")
fields.MsgType = ProtoField.uint8("rls.message_type", "Message Type", base.DEC, msgTypeNames)
fields.Sti = ProtoField.uint64("rls.sti", "Sender Node Temporary ID", base.DEC)
fields.PduType = ProtoField.uint8("rls.pdu_type", "PDU Type", base.DEC, pduTypeNames)
fields.PduId = ProtoField.uint32("rls.pdu_id", "PDU ID", base.DEC)
fields.RrcMsgType = ProtoField.uint32("rls.rrc_message_type", "RRC Message Type", base.DEC, rrcMsgTypeNames)
fields.PduLength = ProtoField.uint32("rls.pdu_length", "PDU Length", base.DEC)
fields.PduSessionId = ProtoField.uint32("rls.pdu_session_id", "PDU Session ID", base.DEC)
fields.AcknowledgeItem = ProtoField.uint32("rls.ack_item", "PDU ID")
fields.Dbm = ProtoField.int32("rls.dbm", "RLS Signal Strength (dBm)", base.DEC)
fields.PosX = ProtoField.uint32("rls.pos_x", "RLS Position X", base.DEC)
fields.PosY = ProtoField.uint32("rls.pos_y", "RLS Position Y", base.DEC)
fields.PosZ = ProtoField.uint32("rls.pos_z", "RLS Position Z", base.DEC)

function rlsProtocol.dissector(buffer, pinfo, tree)
    if buffer:len() == 0 then return end
    if buffer(0, 1):uint() ~= 0x03 then return end

    pinfo.cols.protocol = rlsProtocol.name

    local versionNumber = buffer(1, 1):uint() .. "." .. buffer(2, 1):uint() .. "." .. buffer(3, 1):uint()
    local subtree = tree:add(rlsProtocol, buffer(), "UERANSIM Radio Link Simulation (RLS) protocol")

    subtree:add(fields.Version, buffer(1, 3), versionNumber)
    subtree:add(fields.MsgType, buffer(4, 1))
    local msgType = buffer(4, 1):uint()

    pinfo.cols.info = msgTypeNames[msgType]
    subtree:add(fields.Sti, buffer(5, 8))

    if msgType == 4 then -- Heartbeat
        subtree:add(fields.PosX, buffer(13,4))
        subtree:add(fields.PosY, buffer(17,4))
        subtree:add(fields.PosZ, buffer(21,4))
    elseif msgType == 5 then -- Heartbeat ACK
        subtree:add(fields.Dbm, buffer(13,4))
    elseif msgType == 6 then -- PDU Transmission
        local pduType = buffer(13, 1):uint()
        subtree:add(fields.PduType, buffer(13, 1))
        subtree:add(fields.PduId, buffer(14, 4))
        if pduType == 1 then -- RRC PDU
            local rrcMsgType = buffer(18, 4):uint()
            local pduLength = buffer(22, 4):uint()
            subtree:add(fields.RrcMsgType, buffer(18, 4))
            subtree:add(fields.PduLength, buffer(22, 4))
            Dissector.get(nrRrcDissectors[rrcMsgType]):call(buffer(26, pduLength):tvb(), pinfo, tree)
        elseif (pduType == 2) then -- Data PDU
            subtree:add(fields.PduSessionId, buffer(18, 4))
            local pduLength = buffer(22, 4):uint()
            subtree:add(fields.PduLength, buffer(22, 4))
            Dissector.get("ip"):call(buffer(26, pduLength):tvb(), pinfo, tree)
        end
    elseif msgType == 7 then -- PDU Transmission ACK
        local ackCount = buffer(13, 4):uint()
        local ackArray = subtree:add(rlsProtocol, buffer(13, 4), "Acknowledge List (" .. ackCount .. ")")
        for i = 1,ackCount,1 do
            ackArray:add(fields.AcknowledgeItem, buffer(17 + (i - 1) * 4, 4))
        end
    end
end

local udp_port = DissectorTable.get("udp.port")
udp_port:add(4997, rlsProtocol)
