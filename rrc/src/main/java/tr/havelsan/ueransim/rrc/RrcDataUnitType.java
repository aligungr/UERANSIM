/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc;

import tr.havelsan.ueransim.asn.core.AsnValue;
import tr.havelsan.ueransim.rrc.asn.bit_strings.*;
import tr.havelsan.ueransim.rrc.asn.booleans.RRC_EUTRA_PresenceAntennaPort1;
import tr.havelsan.ueransim.rrc.asn.choices.*;
import tr.havelsan.ueransim.rrc.asn.enums.*;
import tr.havelsan.ueransim.rrc.asn.integers.*;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;
import tr.havelsan.ueransim.rrc.asn.sequences.*;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

public enum RrcDataUnitType {
    AccessStratumRelease(1),
    AdditionalSpectrumEmission(2),
    AffectedCarrierFreqCombEUTRA(3),
    AffectedCarrierFreqCombInfoMRDC(4),
    AffectedCarrierFreqCombNR(5),
    AggregatedBandwidth(6),
    Alpha(7),
    AMF_Identifier(8),
    ARFCN_ValueEUTRA(9),
    ARFCN_ValueNR(10),
    AS_Config(11),
    AS_Context(12),
    //asn_constant(13),
    BandCombination(14),
    BandCombinationIndex(15),
    BandCombinationInfo(16),
    BandCombinationInfoList(17),
    BandCombinationInfoSN(18),
    BandCombinationList(19),
    BandCombinationList_v1540(20),
    BandCombinationList_v1550(21),
    BandCombinationList_v1560(22),
    BandCombination_v1540(23),
    BandCombination_v1550(24),
    BandCombination_v1560(25),
    BandEntryIndex(26),
    BandNR(27),
    BandParameters(28),
    BandParameters_v1540(29),
    BCCH_BCH_Message(30),
    BCCH_BCH_MessageType(31),
    BCCH_Config(32),
    BCCH_DL_SCH_Message(33),
    BCCH_DL_SCH_MessageType(34),
    BeamFailureRecoveryConfig(35),
    BeamManagementSSB_CSI_RS(36),
    BetaOffsets(37),
    BFR_CSIRS_Resource(38),
    BFR_SSB_Resource(39),
    BSR_Config(40),
    BWP_DownlinkCommon(41),
    BWP_DownlinkDedicated(42),
    BWP_Downlink(43),
    BWP(44),
    BWP_Id(45),
    BWP_UplinkCommon(46),
    BWP_UplinkDedicated(47),
    BWP_Uplink(48),
    CA_BandwidthClassEUTRA(49),
    CA_BandwidthClassNR(50),
    CandidateServingFreqListEUTRA(51),
    CandidateServingFreqListNR(52),
    CA_ParametersEUTRA(53),
    CA_ParametersEUTRA_v1560(54),
    CA_ParametersNRDC(55),
    CA_ParametersNR(56),
    CA_ParametersNR_v1540(57),
    CA_ParametersNR_v1550(58),
    CA_ParametersNR_v1560(59),
    CarrierFreqEUTRA(60),
    CarrierFreqListEUTRA(61),
    CarrierInfoNR(62),
    CellAccessRelatedInfo_EUTRA_5GC(63),
    CellAccessRelatedInfo_EUTRA_EPC(64),
    CellAccessRelatedInfo(65),
    CellGroupConfig(66),
    CellGroupId(67),
    CellIdentity_EUTRA_5GC(68),
    CellIdentity(69),
    CellReselectionPriorities(70),
    CellReselectionPriority(71),
    CellReselectionSubPriority(72),
    CellsToAddMod(73),
    CellsToAddModList(74),
    CellsTriggeredList(75),
    CFRA_CSIRS_Resource(76),
    CFRA(77),
    CFRA_SSB_Resource(78),
    CG_Config(79),
    CG_Config_IEs(80),
    CG_ConfigInfo(81),
    CG_ConfigInfo_IEs(82),
    CG_ConfigInfo_v1540_IEs(83),
    CG_ConfigInfo_v1560_IEs(84),
    CG_Config_v1540_IEs(85),
    CG_Config_v1560_IEs(86),
    CGI_InfoEUTRA(87),
    CGI_InfoNR(88),
    CG_UCI_OnPUSCH(89),
    CipheringAlgorithm(90),
    CodebookConfig(91),
    CodebookParameters(92),
    ConfigRestrictInfoSCG(93),
    ConfigRestrictModReqSCG(94),
    ConfiguredGrantConfig(95),
    ConnEstFailureControl(96),
    ControlResourceSet(97),
    ControlResourceSetId(98),
    ControlResourceSetZero(99),
    CounterCheck(100),
    CounterCheck_IEs(101),
    CounterCheckResponse(102),
    CounterCheckResponse_IEs(103),
    CrossCarrierSchedulingConfig(104),
    CSI_AperiodicTriggerState(105),
    CSI_AperiodicTriggerStateList(106),
    CSI_AssociatedReportConfigInfo(107),
    CSI_FrequencyOccupation(108),
    CSI_IM_Resource(109),
    CSI_IM_ResourceId(110),
    CSI_IM_ResourceSet(111),
    CSI_IM_ResourceSetId(112),
    CSI_MeasConfig(113),
    CSI_ReportConfig(114),
    CSI_ReportConfigId(115),
    CSI_ReportFramework(116),
    CSI_ReportPeriodicityAndOffset(117),
    CSI_ResourceConfig(118),
    CSI_ResourceConfigId(119),
    CSI_ResourcePeriodicityAndOffset(120),
    CSI_RS_CellMobility(121),
    CSI_RS_ForTracking(122),
    CSI_RS_IM_ReceptionForFeedback(123),
    CSI_RS_Index(124),
    CSI_RS_ProcFrameworkForSRS(125),
    CSI_RS_ResourceConfigMobility(126),
    CSI_RS_ResourceMapping(127),
    CSI_RS_Resource_Mobility(128),
    CSI_SemiPersistentOnPUSCH_TriggerState(129),
    CSI_SemiPersistentOnPUSCH_TriggerStateList(130),
    CSI_SSB_ResourceSet(131),
    CSI_SSB_ResourceSetId(132),
    DataInactivityTimer(133),
    DedicatedNAS_Message(134),
    DelayBudgetReport(135),
    DL_AM_RLC(136),
    DL_CCCH_Message(137),
    DL_CCCH_MessageType(138),
    DL_DCCH_Message(139),
    DL_DCCH_MessageType(140),
    DLInformationTransfer(141),
    DLInformationTransfer_IEs(142),
    DL_UM_RLC(143),
    DMRS_DownlinkConfig(144),
    DMRS_UplinkConfig(145),
    DownlinkConfigCommon(146),
    DownlinkConfigCommonSIB(147),
    DownlinkPreemption(148),
    DRB_CountInfo(149),
    DRB_CountInfoList(150),
    DRB_CountMSB_Info(151),
    DRB_CountMSB_InfoList(152),
    DRB_Identity(153),
    DRB_ToAddMod(154),
    DRB_ToAddModList(155),
    DRB_ToReleaseList(156),
    DRX_Config(157),
    DRX_Info(158),
    DummyA(159),
    DummyB(160),
    DummyC(161),
    DummyD(162),
    DummyE(163),
    DummyF(164),
    DummyG(165),
    DummyH(166),
    DummyI(167),
    EstablishmentCause(168),
    EUTRA_AllowedMeasBandwidth(169),
    EUTRA_BlackCell(170),
    EUTRA_Cell(171),
    EUTRA_CellIndex(172),
    EUTRA_CellIndexList(173),
    EUTRA_FreqBlackCellList(174),
    EUTRA_FreqNeighCellInfo(175),
    EUTRA_FreqNeighCellList(176),
    EUTRA_MBSFN_SubframeConfig(177),
    EUTRA_MBSFN_SubframeConfigList(178),
    EUTRA_MultiBandInfo(179),
    EUTRA_MultiBandInfoList(180),
    EUTRA_NS_PmaxList(181),
    EUTRA_NS_PmaxValue(182),
    EUTRA_ParametersCommon(183),
    EUTRA_Parameters(184),
    EUTRA_ParametersXDD_Diff(185),
    EUTRA_PhysCellId(186),
    EUTRA_PhysCellIdRange(187),
    EUTRA_PresenceAntennaPort1(188),
    EUTRA_Q_OffsetRange(189),
    EUTRA_RSTD_Info(190),
    EUTRA_RSTD_InfoList(191),
    EventTriggerConfig(192),
    EventTriggerConfigInterRAT(193),
    FailureInfoRLC_Bearer(194),
    FailureInformation(195),
    FailureInformation_IEs(196),
    FailureReportSCG_EUTRA(197),
    FailureReportSCG(198),
    FeatureSetCombination(199),
    FeatureSetCombinationId(200),
    FeatureSetDownlink(201),
    FeatureSetDownlinkId(202),
    FeatureSetDownlinkPerCC(203),
    FeatureSetDownlinkPerCC_Id(204),
    FeatureSetDownlink_v1540(205),
    FeatureSetEntryIndex(206),
    FeatureSetEUTRA_DownlinkId(207),
    FeatureSetEUTRA_UplinkId(208),
    FeatureSet(209),
    FeatureSets(210),
    FeatureSetsPerBand(211),
    FeatureSetUplink(212),
    FeatureSetUplinkId(213),
    FeatureSetUplinkPerCC(214),
    FeatureSetUplinkPerCC_Id(215),
    FeatureSetUplinkPerCC_v1540(216),
    FeatureSetUplink_v1540(217),
    FilterCoefficient(218),
    FilterConfig(219),
    FreqBandIndicatorEUTRA(220),
    FreqBandIndicatorNR(221),
    FreqBandInformationEUTRA(222),
    FreqBandInformation(223),
    FreqBandInformationNR(224),
    FreqBandList(225),
    FreqPriorityEUTRA(226),
    FreqPriorityListEUTRA(227),
    FreqPriorityListNR(228),
    FreqPriorityNR(229),
    FreqSeparationClass(230),
    FrequencyInfoDL(231),
    FrequencyInfoDL_SIB(232),
    FrequencyInfoUL(233),
    FrequencyInfoUL_SIB(234),
    FR_Info(235),
    FR_InfoList(236),
    GapConfig(237),
    GeneralParametersMRDC_XDD_Diff(238),
    HandoverCommand(239),
    HandoverCommand_IEs(240),
    HandoverPreparationInformation(241),
    HandoverPreparationInformation_IEs(242),
    Hysteresis(243),
    IMS_ParametersCommon(244),
    IMS_ParametersFRX_Diff(245),
    IMS_Parameters(246),
    InitialUE_Identity(247),
    INT_ConfigurationPerServingCell(248),
    IntegrityProtAlgorithm(249),
    InterFreqBlackCellList(250),
    InterFreqCarrierFreqInfo(251),
    InterFreqCarrierFreqList(252),
    InterFreqNeighCellInfo(253),
    InterFreqNeighCellList(254),
    InterRAT_Parameters(255),
    IntraFreqBlackCellList(256),
    IntraFreqNeighCellInfo(257),
    IntraFreqNeighCellList(258),
    I_RNTI_Value(259),
    LocationMeasurementIndication(260),
    LocationMeasurementIndication_IEs(261),
    LocationMeasurementInfo(262),
    LogicalChannelConfig(263),
    LogicalChannelIdentity(264),
    MAC_CellGroupConfig(265),
    MAC_ParametersCommon(266),
    MAC_Parameters(267),
    MAC_ParametersXDD_Diff(268),
    MasterKeyUpdate(269),
    MCC(270),
    MCC_MNC_Digit(271),
    MeasAndMobParametersCommon(272),
    MeasAndMobParametersFRX_Diff(273),
    MeasAndMobParameters(274),
    MeasAndMobParametersMRDC_Common(275),
    MeasAndMobParametersMRDC_FRX_Diff(276),
    MeasAndMobParametersMRDC(277),
    MeasAndMobParametersMRDC_v1560(278),
    MeasAndMobParametersMRDC_XDD_Diff(279),
    MeasAndMobParametersMRDC_XDD_Diff_v1560(280),
    MeasAndMobParametersXDD_Diff(281),
    MeasConfig(282),
    MeasConfigMN(283),
    MeasConfigSN(284),
    MeasGapConfig(285),
    MeasGapSharingConfig(286),
    MeasGapSharingScheme(287),
    MeasId(288),
    MeasIdToAddMod(289),
    MeasIdToAddModList(290),
    MeasIdToRemoveList(291),
    MeasObjectEUTRA(292),
    MeasObjectId(293),
    MeasObjectNR(294),
    MeasObjectToAddMod(295),
    MeasObjectToAddModList(296),
    MeasObjectToRemoveList(297),
    MeasQuantityResultsEUTRA(298),
    MeasQuantityResults(299),
    MeasReportQuantity(300),
    MeasResult2EUTRA(301),
    MeasResult2NR(302),
    MeasResultCellListSFTD_EUTRA(303),
    MeasResultCellListSFTD_NR(304),
    MeasResultCellSFTD_NR(305),
    MeasResultEUTRA(306),
    MeasResultFreqListFailMRDC(307),
    MeasResultFreqList(308),
    MeasResultList2NR(309),
    MeasResultListEUTRA(310),
    MeasResultListNR(311),
    MeasResultNR(312),
    MeasResultSCG_Failure(313),
    MeasResultServFreqListEUTRA_SCG(314),
    MeasResultServFreqListNR_SCG(315),
    MeasResultServMO(316),
    MeasResultServMOList(317),
    MeasResultSFTD_EUTRA(318),
    MeasResults(319),
    MeasTiming(320),
    MeasTimingList(321),
    MeasTriggerQuantityEUTRA(322),
    MeasTriggerQuantity(323),
    MeasTriggerQuantityOffset(324),
    MeasurementReport(325),
    MeasurementReport_IEs(326),
    MeasurementTimingConfiguration(327),
    MeasurementTimingConfiguration_IEs(328),
    MeasurementTimingConfiguration_v1550_IEs(329),
    MIB(330),
    MIMO_LayersDL(331),
    MIMO_LayersUL(332),
    MIMO_ParametersPerBand(333),
    MNC(334),
    MobilityFromNRCommand(335),
    MobilityFromNRCommand_IEs(336),
    MobilityStateParameters(337),
    ModulationOrder(338),
    MRDC_AssistanceInfo(339),
    MRDC_Parameters(340),
    MRDC_SecondaryCellGroupConfig(341),
    MultiBandInfoListEUTRA(342),
    MultiFrequencyBandListNR(343),
    MultiFrequencyBandListNR_SIB(344),
    NAICS_Capability_Entry(345),
    NextHopChainingCount(346),
    NG_5G_S_TMSI(347),
    NRDC_Parameters(348),
    NR_FreqInfo(349),
    NR_MultiBandInfo(350),
    NR_NS_PmaxList(351),
    NR_NS_PmaxValue(352),
    NR_RS_Type(353),
    NumberOfCarriers(354),
    NZP_CSI_RS_Resource(355),
    NZP_CSI_RS_ResourceId(356),
    NZP_CSI_RS_ResourceSet(357),
    NZP_CSI_RS_ResourceSetId(358),
    OtherConfig(359),
    OtherConfig_v1540(360),
    OverheatingAssistanceConfig(361),
    OverheatingAssistance(362),
    P0_PUCCH(363),
    P0_PUCCH_Id(364),
    P0_PUSCH_AlphaSet(365),
    P0_PUSCH_AlphaSetId(366),
    PagingCycle(367),
    Paging(368),
    PagingRecord(369),
    PagingRecordList(370),
    PagingUE_Identity(371),
    PCCH_Config(372),
    PCCH_Message(373),
    PCCH_MessageType(374),
    PCI_List(375),
    PCI_RangeElement(376),
    PCI_Range(377),
    PCI_RangeIndex(378),
    PCI_RangeIndexList(379),
    PDCCH_BlindDetection(380),
    PDCCH_ConfigCommon(381),
    PDCCH_Config(382),
    PDCCH_ConfigSIB1(383),
    PDCCH_ServingCellConfig(384),
    PDCP_Config(385),
    PDCP_Parameters(386),
    PDCP_ParametersMRDC(387),
    PDSCH_CodeBlockGroupTransmission(388),
    PDSCH_ConfigCommon(389),
    PDSCH_Config(390),
    PDSCH_ServingCellConfig(391),
    PDSCH_TimeDomainResourceAllocation(392),
    PDSCH_TimeDomainResourceAllocationList(393),
    PDU_SessionID(394),
    PeriodicalReportConfig(395),
    PeriodicalReportConfigInterRAT(396),
    PeriodicRNAU_TimerValue(397),
    PH_InfoMCG(398),
    PH_InfoSCG(399),
    PHR_Config(400),
    PH_TypeListMCG(401),
    PH_TypeListSCG(402),
    PH_UplinkCarrierMCG(403),
    PH_UplinkCarrierSCG(404),
    Phy_ParametersCommon(405),
    Phy_ParametersFR1(406),
    Phy_ParametersFR2(407),
    Phy_ParametersFRX_Diff(408),
    Phy_Parameters(409),
    Phy_ParametersMRDC(410),
    Phy_ParametersXDD_Diff(411),
    PhysCellId(412),
    PhysicalCellGroupConfig(413),
    PLMN_Identity_EUTRA_5GC(414),
    PLMN_Identity(415),
    PLMN_IdentityInfo(416),
    PLMN_IdentityInfoList(417),
    PLMN_IdentityList_EUTRA_5GC(418),
    PLMN_IdentityList_EUTRA_EPC(419),
    PLMN_RAN_AreaCell(420),
    PLMN_RAN_AreaCellList(421),
    PLMN_RAN_AreaConfig(422),
    PLMN_RAN_AreaConfigList(423),
    P_Max(424),
    PollByte(425),
    PollPDU(426),
    PortIndex2(427),
    PortIndex4(428),
    PortIndex8(429),
    PortIndexFor8Ranks(430),
    PRACH_ResourceDedicatedBFR(431),
    PRB_Id(432),
    ProcessingParameters(433),
    PTRS_DensityRecommendationDL(434),
    PTRS_DensityRecommendationUL(435),
    PTRS_DownlinkConfig(436),
    PTRS_UplinkConfig(437),
    PUCCH_ConfigCommon(438),
    PUCCH_Config(439),
    PUCCH_CSI_Resource(440),
    PUCCH_format0(441),
    PUCCH_format1(442),
    PUCCH_format2(443),
    PUCCH_format3(444),
    PUCCH_format4(445),
    PUCCH_FormatConfig(446),
    PUCCH_MaxCodeRate(447),
    PUCCH_PathlossReferenceRS(448),
    PUCCH_PathlossReferenceRS_Id(449),
    PUCCH_PowerControl(450),
    PUCCH_Resource(451),
    PUCCH_ResourceId(452),
    PUCCH_ResourceSet(453),
    PUCCH_ResourceSetId(454),
    PUCCH_SpatialRelationInfo(455),
    PUCCH_SpatialRelationInfoId(456),
    PUCCH_TPC_CommandConfig(457),
    PUSCH_CodeBlockGroupTransmission(458),
    PUSCH_ConfigCommon(459),
    PUSCH_Config(460),
    PUSCH_PathlossReferenceRS(461),
    PUSCH_PathlossReferenceRS_Id(462),
    PUSCH_PowerControl(463),
    PUSCH_ServingCellConfig(464),
    PUSCH_TimeDomainResourceAllocation(465),
    PUSCH_TimeDomainResourceAllocationList(466),
    PUSCH_TPC_CommandConfig(467),
    QCL_Info(468),
    QFI(469),
    Q_OffsetRange(470),
    Q_OffsetRangeList(471),
    Q_QualMin(472),
    Q_RxLevMin(473),
    QuantityConfig(474),
    QuantityConfigNR(475),
    QuantityConfigRS(476),
    RACH_ConfigCommon(477),
    RACH_ConfigDedicated(478),
    RACH_ConfigGeneric(479),
    RadioBearerConfig(480),
    RadioLinkMonitoringConfig(481),
    RadioLinkMonitoringRS(482),
    RadioLinkMonitoringRS_Id(483),
    RAN_AreaCode(484),
    RAN_AreaConfig(485),
    RangeToBestCell(486),
    RAN_NotificationAreaInfo(487),
    RA_Prioritization(488),
    RateMatchPatternGroup(489),
    RateMatchPattern(490),
    RateMatchPatternId(491),
    RateMatchPatternLTE_CRS(492),
    RAT_Type(493),
    ReconfigurationWithSync(494),
    RedirectedCarrierInfo_EUTRA(495),
    RedirectedCarrierInfo(496),
    ReducedAggregatedBandwidth(497),
    ReestablishmentCause(498),
    ReestablishmentInfo(499),
    ReestabNCellInfo(500),
    ReestabNCellInfoList(501),
    ReestabUE_Identity(502),
    ReferenceSignalConfig(503),
    RegisteredAMF(504),
    RejectWaitTime(505),
    ReportCGI_EUTRA(506),
    ReportCGI(507),
    ReportConfigId(508),
    ReportConfigInterRAT(509),
    ReportConfigNR(510),
    ReportConfigToAddMod(511),
    ReportConfigToAddModList(512),
    ReportConfigToRemoveList(513),
    ReportInterval(514),
    ReportSFTD_EUTRA(515),
    ReportSFTD_NR(516),
    ReselectionThreshold(517),
    ReselectionThresholdQ(518),
    ResultsPerCSI_RS_Index(519),
    ResultsPerCSI_RS_IndexList(520),
    ResultsPerSSB_Index(521),
    ResultsPerSSB_IndexList(522),
    ResumeCause(523),
    RF_Parameters(524),
    RF_ParametersMRDC(525),
    RLC_BearerConfig(526),
    RLC_Config(527),
    RLC_Parameters(528),
    RLF_TimersAndConstants(529),
    RNTI_Value(530),
    RRCReconfigurationComplete(531),
    RRCReconfigurationComplete_IEs(532),
    RRCReconfigurationComplete_v1530_IEs(533),
    RRCReconfigurationComplete_v1560_IEs(534),
    RRCReconfiguration(535),
    RRCReconfiguration_IEs(536),
    RRCReconfiguration_v1530_IEs(537),
    RRCReconfiguration_v1540_IEs(538),
    RRCReconfiguration_v1560_IEs(539),
    RRCReestablishmentComplete(540),
    RRCReestablishmentComplete_IEs(541),
    RRCReestablishment(542),
    RRCReestablishment_IEs(543),
    RRCReestablishmentRequest(544),
    RRCReestablishmentRequest_IEs(545),
    RRCReject(546),
    RRCReject_IEs(547),
    RRCRelease(548),
    RRCRelease_IEs(549),
    RRCRelease_v1540_IEs(550),
    RRCResumeComplete(551),
    RRCResumeComplete_IEs(552),
    RRCResume(553),
    RRCResume_IEs(554),
    RRCResumeRequest1(555),
    RRCResumeRequest1_IEs(556),
    RRCResumeRequest(557),
    RRCResumeRequest_IEs(558),
    RRCResume_v1560_IEs(559),
    RRCSetupComplete(560),
    RRCSetupComplete_IEs(561),
    RRCSetup(562),
    RRCSetup_IEs(563),
    RRCSetupRequest(564),
    RRCSetupRequest_IEs(565),
    RRCSystemInfoRequest(566),
    RRCSystemInfoRequest_r15_IEs(567),
    RRC_TransactionIdentifier(568),
    RRM_Config(569),
    RSRP_RangeEUTRA(570),
    RSRP_Range(571),
    RSRQ_RangeEUTRA(572),
    RSRQ_Range(573),
    SCellConfig(574),
    SCellIndex(575),
    SCGFailureInformationEUTRA(576),
    SCGFailureInformationEUTRA_IEs(577),
    SCGFailureInformation(578),
    SCGFailureInformation_IEs(579),
    SchedulingInfo(580),
    SchedulingRequestConfig(581),
    SchedulingRequestId(582),
    SchedulingRequestResourceConfig(583),
    SchedulingRequestResourceId(584),
    SchedulingRequestToAddMod(585),
    ScramblingId(586),
    SCS_SpecificCarrier(587),
    SDAP_Config(588),
    SDAP_Parameters(589),
    SearchSpace(590),
    SearchSpaceId(591),
    SearchSpaceZero(592),
    SecurityAlgorithmConfig(593),
    SecurityConfig(594),
    SecurityConfigSMC(595),
    SecurityModeCommand(596),
    SecurityModeCommand_IEs(597),
    SecurityModeComplete(598),
    SecurityModeComplete_IEs(599),
    SecurityModeFailure(600),
    SecurityModeFailure_IEs(601),
    ServCellIndex(602),
    ServingCellConfigCommon(603),
    ServingCellConfigCommonSIB(604),
    ServingCellConfig(605),
    //SetupRelease(606),
    ShortI_RNTI_Value(607),
    ShortMAC_I(608),
    SIB1(609),
    SIB2(610),
    SIB3(611),
    SIB4(612),
    SIB5(613),
    SIB6(614),
    SIB7(615),
    SIB8(616),
    SIB9(617),
    SIB_Mapping(618),
    SIB_TypeInfo(619),
    SINR_RangeEUTRA(620),
    SINR_Range(621),
    SI_RequestConfig(622),
    SI_RequestResources(623),
    SI_SchedulingInfo(624),
    SK_Counter(625),
    SlotFormatCombination(626),
    SlotFormatCombinationId(627),
    SlotFormatCombinationsPerCell(628),
    SlotFormatIndicator(629),
    SN_FieldLengthAM(630),
    SN_FieldLengthUM(631),
    S_NSSAI(632),
    SpatialRelations(633),
    SpCellConfig(634),
    SpeedStateScaleFactors(635),
    SPS_Config(636),
    SRB_Identity(637),
    SRB_ToAddMod(638),
    SRB_ToAddModList(639),
    SRI_PUSCH_PowerControl(640),
    SRI_PUSCH_PowerControlId(641),
    SRS_CarrierSwitching(642),
    SRS_CC_SetIndex(643),
    SRS_Config(644),
    SRS_PeriodicityAndOffset(645),
    SRS_Resource(646),
    SRS_ResourceId(647),
    SRS_ResourceSet(648),
    SRS_ResourceSetId(649),
    SRS_Resources(650),
    SRS_SpatialRelationInfo(651),
    SRS_SwitchingTimeEUTRA(652),
    SRS_SwitchingTimeNR(653),
    SRS_TPC_CommandConfig(654),
    SRS_TPC_PDCCH_Config(655),
    SSB_ConfigMobility(656),
    SSB_Index(657),
    SSB_MTC2(658),
    SSB_MTC(659),
    SSB_ToMeasure(660),
    SS_RSSI_Measurement(661),
    SubcarrierSpacing(662),
    SupportedBandwidth(663),
    SupportedCSI_RS_Resource(664),
    SuspendConfig(665),
    SystemInformation(666),
    SystemInformation_IEs(667),
    TAG_Config(668),
    TAG(669),
    TAG_Id(670),
    TCI_State(671),
    TCI_StateId(672),
    TDD_UL_DL_ConfigCommon(673),
    TDD_UL_DL_ConfigDedicated(674),
    TDD_UL_DL_Pattern(675),
    TDD_UL_DL_SlotConfig(676),
    TDD_UL_DL_SlotIndex(677),
    ThresholdNR(678),
    TimeAlignmentTimer(679),
    TimeToTrigger(680),
    T_PollRetransmit(681),
    TrackingAreaCode(682),
    T_Reassembly(683),
    T_Reselection(684),
    T_StatusProhibit(685),
    UAC_AccessCategory1_SelectionAssistanceInfo(686),
    UAC_BarringInfoSet(687),
    UAC_BarringInfoSetIndex(688),
    UAC_BarringInfoSetList(689),
    UAC_BarringPerCat(690),
    UAC_BarringPerCatList(691),
    UAC_BarringPerPLMN(692),
    UAC_BarringPerPLMN_List(693),
    UCI_OnPUSCH(694),
    UEAssistanceInformation(695),
    UEAssistanceInformation_IEs(696),
    UEAssistanceInformation_v1540_IEs(697),
    UECapabilityEnquiry(698),
    UECapabilityEnquiry_IEs(699),
    UECapabilityEnquiry_v1560_IEs(700),
    UECapabilityInformation(701),
    UECapabilityInformation_IEs(702),
    UE_CapabilityRAT_Container(703),
    UE_CapabilityRAT_ContainerList(704),
    UE_CapabilityRAT_Request(705),
    UE_CapabilityRAT_RequestList(706),
    UE_CapabilityRequestFilterCommon(707),
    UE_CapabilityRequestFilterNR(708),
    UE_CapabilityRequestFilterNR_v1540(709),
    UE_MRDC_CapabilityAddFRX_Mode(710),
    UE_MRDC_CapabilityAddXDD_Mode(711),
    UE_MRDC_CapabilityAddXDD_Mode_v1560(712),
    UE_MRDC_Capability(713),
    UE_MRDC_Capability_v1560(714),
    UE_NR_CapabilityAddFRX_Mode(715),
    UE_NR_CapabilityAddFRX_Mode_v1540(716),
    UE_NR_CapabilityAddXDD_Mode(717),
    UE_NR_CapabilityAddXDD_Mode_v1530(718),
    UE_NR_Capability(719),
    UE_NR_Capability_v1530(720),
    UE_NR_Capability_v1540(721),
    UE_NR_Capability_v1550(722),
    UE_NR_Capability_v1560(723),
    UERadioAccessCapabilityInformation(724),
    UERadioAccessCapabilityInformation_IEs(725),
    UERadioPagingInformation(726),
    UERadioPagingInformation_IEs(727),
    UE_TimersAndConstants(728),
    UL_AM_RLC(729),
    UL_CCCH1_Message(730),
    UL_CCCH1_MessageType(731),
    UL_CCCH_Message(732),
    UL_CCCH_MessageType(733),
    UL_DataSplitThreshold(734),
    UL_DCCH_Message(735),
    UL_DCCH_MessageType(736),
    ULInformationTransfer(737),
    ULInformationTransfer_IEs(738),
    ULInformationTransferMRDC(739),
    ULInformationTransferMRDC_IEs(740),
    UL_UM_RLC(741),
    UplinkConfigCommon(742),
    UplinkConfigCommonSIB(743),
    UplinkConfig(744),
    UplinkTxDirectCurrentBWP(745),
    UplinkTxDirectCurrentCell(746),
    UplinkTxDirectCurrentList(747),
    VarMeasConfig(748),
    VarMeasReport(749),
    VarMeasReportList(750),
    VarPendingRNA_Update(751),
    VarResumeMAC_Input(752),
    VarShortMAC_Input(753),
    VictimSystemType(754),
    ZP_CSI_RS_Resource(755),
    ZP_CSI_RS_ResourceId(756),
    ZP_CSI_RS_ResourceSet(757),
    ZP_CSI_RS_ResourceSetId(758);

    public final int value;

    RrcDataUnitType(int value) {
        this.value = value;
    }

    public Class<? extends AsnValue> getPodType() {
        switch (this) {
            case AccessStratumRelease:
                return RRC_AccessStratumRelease.class;
            case AdditionalSpectrumEmission:
                return RRC_AdditionalSpectrumEmission.class;
            case AffectedCarrierFreqCombEUTRA:
                return RRC_AffectedCarrierFreqCombEUTRA.class;
            case AffectedCarrierFreqCombInfoMRDC:
                return RRC_AffectedCarrierFreqCombInfoMRDC.class;
            case AffectedCarrierFreqCombNR:
                return RRC_AffectedCarrierFreqCombNR.class;
            case AggregatedBandwidth:
                return RRC_AggregatedBandwidth.class;
            case Alpha:
                return RRC_Alpha.class;
            case AMF_Identifier:
                return RRC_AMF_Identifier.class;
            case ARFCN_ValueEUTRA:
                return RRC_ARFCN_ValueEUTRA.class;
            case ARFCN_ValueNR:
                return RRC_ARFCN_ValueNR.class;
            case AS_Config:
                return RRC_AS_Config.class;
            case AS_Context:
                return RRC_AS_Context.class;
            case BandCombination:
                return RRC_BandCombination.class;
            case BandCombinationIndex:
                return RRC_BandCombinationIndex.class;
            case BandCombinationInfo:
                return RRC_BandCombinationInfo.class;
            case BandCombinationInfoList:
                return RRC_BandCombinationInfoList.class;
            case BandCombinationInfoSN:
                return RRC_BandCombinationInfoSN.class;
            case BandCombinationList:
                return RRC_BandCombinationList.class;
            case BandCombinationList_v1540:
                return RRC_BandCombinationList_v1540.class;
            case BandCombinationList_v1550:
                return RRC_BandCombinationList_v1550.class;
            case BandCombinationList_v1560:
                return RRC_BandCombinationList_v1560.class;
            case BandCombination_v1540:
                return RRC_BandCombination_v1540.class;
            case BandCombination_v1550:
                return RRC_BandCombination_v1550.class;
            case BandCombination_v1560:
                return RRC_BandCombination_v1560.class;
            case BandEntryIndex:
                return RRC_BandEntryIndex.class;
            case BandNR:
                return RRC_BandNR.class;
            case BandParameters:
                return RRC_BandParameters.class;
            case BandParameters_v1540:
                return RRC_BandParameters_v1540.class;
            case BCCH_BCH_Message:
                return RRC_BCCH_BCH_Message.class;
            case BCCH_BCH_MessageType:
                return RRC_BCCH_BCH_MessageType.class;
            case BCCH_Config:
                return RRC_BCCH_Config.class;
            case BCCH_DL_SCH_Message:
                return RRC_BCCH_DL_SCH_Message.class;
            case BCCH_DL_SCH_MessageType:
                return RRC_BCCH_DL_SCH_MessageType.class;
            case BeamFailureRecoveryConfig:
                return RRC_BeamFailureRecoveryConfig.class;
            case BeamManagementSSB_CSI_RS:
                return RRC_BeamManagementSSB_CSI_RS.class;
            case BetaOffsets:
                return RRC_BetaOffsets.class;
            case BFR_CSIRS_Resource:
                return RRC_BFR_CSIRS_Resource.class;
            case BFR_SSB_Resource:
                return RRC_BFR_SSB_Resource.class;
            case BSR_Config:
                return RRC_BSR_Config.class;
            case BWP_DownlinkCommon:
                return RRC_BWP_DownlinkCommon.class;
            case BWP_DownlinkDedicated:
                return RRC_BWP_DownlinkDedicated.class;
            case BWP_Downlink:
                return RRC_BWP_Downlink.class;
            case BWP:
                return RRC_BWP.class;
            case BWP_Id:
                return RRC_BWP_Id.class;
            case BWP_UplinkCommon:
                return RRC_BWP_UplinkCommon.class;
            case BWP_UplinkDedicated:
                return RRC_BWP_UplinkDedicated.class;
            case BWP_Uplink:
                return RRC_BWP_Uplink.class;
            case CA_BandwidthClassEUTRA:
                return RRC_CA_BandwidthClassEUTRA.class;
            case CA_BandwidthClassNR:
                return RRC_CA_BandwidthClassNR.class;
            case CandidateServingFreqListEUTRA:
                return RRC_CandidateServingFreqListEUTRA.class;
            case CandidateServingFreqListNR:
                return RRC_CandidateServingFreqListNR.class;
            case CA_ParametersEUTRA:
                return RRC_CA_ParametersEUTRA.class;
            case CA_ParametersEUTRA_v1560:
                return RRC_CA_ParametersEUTRA_v1560.class;
            case CA_ParametersNRDC:
                return RRC_CA_ParametersNRDC.class;
            case CA_ParametersNR:
                return RRC_CA_ParametersNR.class;
            case CA_ParametersNR_v1540:
                return RRC_CA_ParametersNR_v1540.class;
            case CA_ParametersNR_v1550:
                return RRC_CA_ParametersNR_v1550.class;
            case CA_ParametersNR_v1560:
                return RRC_CA_ParametersNR_v1560.class;
            case CarrierFreqEUTRA:
                return RRC_CarrierFreqEUTRA.class;
            case CarrierFreqListEUTRA:
                return RRC_CarrierFreqListEUTRA.class;
            case CarrierInfoNR:
                return RRC_CarrierInfoNR.class;
            case CellAccessRelatedInfo_EUTRA_5GC:
                return RRC_CellAccessRelatedInfo_EUTRA_5GC.class;
            case CellAccessRelatedInfo_EUTRA_EPC:
                return RRC_CellAccessRelatedInfo_EUTRA_EPC.class;
            case CellAccessRelatedInfo:
                return RRC_CellAccessRelatedInfo.class;
            case CellGroupConfig:
                return RRC_CellGroupConfig.class;
            case CellGroupId:
                return RRC_CellGroupId.class;
            case CellIdentity_EUTRA_5GC:
                return RRC_CellIdentity_EUTRA_5GC.class;
            case CellIdentity:
                return RRC_CellIdentity.class;
            case CellReselectionPriorities:
                return RRC_CellReselectionPriorities.class;
            case CellReselectionPriority:
                return RRC_CellReselectionPriority.class;
            case CellReselectionSubPriority:
                return RRC_CellReselectionSubPriority.class;
            case CellsToAddMod:
                return RRC_CellsToAddMod.class;
            case CellsToAddModList:
                return RRC_CellsToAddModList.class;
            case CellsTriggeredList:
                return RRC_CellsTriggeredList.class;
            case CFRA_CSIRS_Resource:
                return RRC_CFRA_CSIRS_Resource.class;
            case CFRA:
                return RRC_CFRA.class;
            case CFRA_SSB_Resource:
                return RRC_CFRA_SSB_Resource.class;
            case CG_Config:
                return RRC_CG_Config.class;
            case CG_Config_IEs:
                return RRC_CG_Config_IEs.class;
            case CG_ConfigInfo:
                return RRC_CG_ConfigInfo.class;
            case CG_ConfigInfo_IEs:
                return RRC_CG_ConfigInfo_IEs.class;
            case CG_ConfigInfo_v1540_IEs:
                return RRC_CG_ConfigInfo_v1540_IEs.class;
            case CG_ConfigInfo_v1560_IEs:
                return RRC_CG_ConfigInfo_v1560_IEs.class;
            case CG_Config_v1540_IEs:
                return RRC_CG_Config_v1540_IEs.class;
            case CG_Config_v1560_IEs:
                return RRC_CG_Config_v1560_IEs.class;
            case CGI_InfoEUTRA:
                return RRC_CGI_InfoEUTRA.class;
            case CGI_InfoNR:
                return RRC_CGI_InfoNR.class;
            case CG_UCI_OnPUSCH:
                return RRC_CG_UCI_OnPUSCH.class;
            case CipheringAlgorithm:
                return RRC_CipheringAlgorithm.class;
            case CodebookConfig:
                return RRC_CodebookConfig.class;
            case CodebookParameters:
                return RRC_CodebookParameters.class;
            case ConfigRestrictInfoSCG:
                return RRC_ConfigRestrictInfoSCG.class;
            case ConfigRestrictModReqSCG:
                return RRC_ConfigRestrictModReqSCG.class;
            case ConfiguredGrantConfig:
                return RRC_ConfiguredGrantConfig.class;
            case ConnEstFailureControl:
                return RRC_ConnEstFailureControl.class;
            case ControlResourceSet:
                return RRC_ControlResourceSet.class;
            case ControlResourceSetId:
                return RRC_ControlResourceSetId.class;
            case ControlResourceSetZero:
                return RRC_ControlResourceSetZero.class;
            case CounterCheck:
                return RRC_CounterCheck.class;
            case CounterCheck_IEs:
                return RRC_CounterCheck_IEs.class;
            case CounterCheckResponse:
                return RRC_CounterCheckResponse.class;
            case CounterCheckResponse_IEs:
                return RRC_CounterCheckResponse_IEs.class;
            case CrossCarrierSchedulingConfig:
                return RRC_CrossCarrierSchedulingConfig.class;
            case CSI_AperiodicTriggerState:
                return RRC_CSI_AperiodicTriggerState.class;
            case CSI_AperiodicTriggerStateList:
                return RRC_CSI_AperiodicTriggerStateList.class;
            case CSI_AssociatedReportConfigInfo:
                return RRC_CSI_AssociatedReportConfigInfo.class;
            case CSI_FrequencyOccupation:
                return RRC_CSI_FrequencyOccupation.class;
            case CSI_IM_Resource:
                return RRC_CSI_IM_Resource.class;
            case CSI_IM_ResourceId:
                return RRC_CSI_IM_ResourceId.class;
            case CSI_IM_ResourceSet:
                return RRC_CSI_IM_ResourceSet.class;
            case CSI_IM_ResourceSetId:
                return RRC_CSI_IM_ResourceSetId.class;
            case CSI_MeasConfig:
                return RRC_CSI_MeasConfig.class;
            case CSI_ReportConfig:
                return RRC_CSI_ReportConfig.class;
            case CSI_ReportConfigId:
                return RRC_CSI_ReportConfigId.class;
            case CSI_ReportFramework:
                return RRC_CSI_ReportFramework.class;
            case CSI_ReportPeriodicityAndOffset:
                return RRC_CSI_ReportPeriodicityAndOffset.class;
            case CSI_ResourceConfig:
                return RRC_CSI_ResourceConfig.class;
            case CSI_ResourceConfigId:
                return RRC_CSI_ResourceConfigId.class;
            case CSI_ResourcePeriodicityAndOffset:
                return RRC_CSI_ResourcePeriodicityAndOffset.class;
            case CSI_RS_CellMobility:
                return RRC_CSI_RS_CellMobility.class;
            case CSI_RS_ForTracking:
                return RRC_CSI_RS_ForTracking.class;
            case CSI_RS_IM_ReceptionForFeedback:
                return RRC_CSI_RS_IM_ReceptionForFeedback.class;
            case CSI_RS_Index:
                return RRC_CSI_RS_Index.class;
            case CSI_RS_ProcFrameworkForSRS:
                return RRC_CSI_RS_ProcFrameworkForSRS.class;
            case CSI_RS_ResourceConfigMobility:
                return RRC_CSI_RS_ResourceConfigMobility.class;
            case CSI_RS_ResourceMapping:
                return RRC_CSI_RS_ResourceMapping.class;
            case CSI_RS_Resource_Mobility:
                return RRC_CSI_RS_Resource_Mobility.class;
            case CSI_SemiPersistentOnPUSCH_TriggerState:
                return RRC_CSI_SemiPersistentOnPUSCH_TriggerState.class;
            case CSI_SemiPersistentOnPUSCH_TriggerStateList:
                return RRC_CSI_SemiPersistentOnPUSCH_TriggerStateList.class;
            case CSI_SSB_ResourceSet:
                return RRC_CSI_SSB_ResourceSet.class;
            case CSI_SSB_ResourceSetId:
                return RRC_CSI_SSB_ResourceSetId.class;
            case DataInactivityTimer:
                return RRC_DataInactivityTimer.class;
            case DedicatedNAS_Message:
                return RRC_DedicatedNAS_Message.class;
            case DelayBudgetReport:
                return RRC_DelayBudgetReport.class;
            case DL_AM_RLC:
                return RRC_DL_AM_RLC.class;
            case DL_CCCH_Message:
                return RRC_DL_CCCH_Message.class;
            case DL_CCCH_MessageType:
                return RRC_DL_CCCH_MessageType.class;
            case DL_DCCH_Message:
                return RRC_DL_DCCH_Message.class;
            case DL_DCCH_MessageType:
                return RRC_DL_DCCH_MessageType.class;
            case DLInformationTransfer:
                return RRC_DLInformationTransfer.class;
            case DLInformationTransfer_IEs:
                return RRC_DLInformationTransfer_IEs.class;
            case DL_UM_RLC:
                return RRC_DL_UM_RLC.class;
            case DMRS_DownlinkConfig:
                return RRC_DMRS_DownlinkConfig.class;
            case DMRS_UplinkConfig:
                return RRC_DMRS_UplinkConfig.class;
            case DownlinkConfigCommon:
                return RRC_DownlinkConfigCommon.class;
            case DownlinkConfigCommonSIB:
                return RRC_DownlinkConfigCommonSIB.class;
            case DownlinkPreemption:
                return RRC_DownlinkPreemption.class;
            case DRB_CountInfo:
                return RRC_DRB_CountInfo.class;
            case DRB_CountInfoList:
                return RRC_DRB_CountInfoList.class;
            case DRB_CountMSB_Info:
                return RRC_DRB_CountMSB_Info.class;
            case DRB_CountMSB_InfoList:
                return RRC_DRB_CountMSB_InfoList.class;
            case DRB_Identity:
                return RRC_DRB_Identity.class;
            case DRB_ToAddMod:
                return RRC_DRB_ToAddMod.class;
            case DRB_ToAddModList:
                return RRC_DRB_ToAddModList.class;
            case DRB_ToReleaseList:
                return RRC_DRB_ToReleaseList.class;
            case DRX_Config:
                return RRC_DRX_Config.class;
            case DRX_Info:
                return RRC_DRX_Info.class;
            case DummyA:
                return RRC_DummyA.class;
            case DummyB:
                return RRC_DummyB.class;
            case DummyC:
                return RRC_DummyC.class;
            case DummyD:
                return RRC_DummyD.class;
            case DummyE:
                return RRC_DummyE.class;
            case DummyF:
                return RRC_DummyF.class;
            case DummyG:
                return RRC_DummyG.class;
            case DummyH:
                return RRC_DummyH.class;
            case DummyI:
                return RRC_DummyI.class;
            case EstablishmentCause:
                return RRC_EstablishmentCause.class;
            case EUTRA_AllowedMeasBandwidth:
                return RRC_EUTRA_AllowedMeasBandwidth.class;
            case EUTRA_BlackCell:
                return RRC_EUTRA_BlackCell.class;
            case EUTRA_Cell:
                return RRC_EUTRA_Cell.class;
            case EUTRA_CellIndex:
                return RRC_EUTRA_CellIndex.class;
            case EUTRA_CellIndexList:
                return RRC_EUTRA_CellIndexList.class;
            case EUTRA_FreqBlackCellList:
                return RRC_EUTRA_FreqBlackCellList.class;
            case EUTRA_FreqNeighCellInfo:
                return RRC_EUTRA_FreqNeighCellInfo.class;
            case EUTRA_FreqNeighCellList:
                return RRC_EUTRA_FreqNeighCellList.class;
            case EUTRA_MBSFN_SubframeConfig:
                return RRC_EUTRA_MBSFN_SubframeConfig.class;
            case EUTRA_MBSFN_SubframeConfigList:
                return RRC_EUTRA_MBSFN_SubframeConfigList.class;
            case EUTRA_MultiBandInfo:
                return RRC_EUTRA_MultiBandInfo.class;
            case EUTRA_MultiBandInfoList:
                return RRC_EUTRA_MultiBandInfoList.class;
            case EUTRA_NS_PmaxList:
                return RRC_EUTRA_NS_PmaxList.class;
            case EUTRA_NS_PmaxValue:
                return RRC_EUTRA_NS_PmaxValue.class;
            case EUTRA_ParametersCommon:
                return RRC_EUTRA_ParametersCommon.class;
            case EUTRA_Parameters:
                return RRC_EUTRA_Parameters.class;
            case EUTRA_ParametersXDD_Diff:
                return RRC_EUTRA_ParametersXDD_Diff.class;
            case EUTRA_PhysCellId:
                return RRC_EUTRA_PhysCellId.class;
            case EUTRA_PhysCellIdRange:
                return RRC_EUTRA_PhysCellIdRange.class;
            case EUTRA_PresenceAntennaPort1:
                return RRC_EUTRA_PresenceAntennaPort1.class;
            case EUTRA_Q_OffsetRange:
                return RRC_EUTRA_Q_OffsetRange.class;
            case EUTRA_RSTD_Info:
                return RRC_EUTRA_RSTD_Info.class;
            case EUTRA_RSTD_InfoList:
                return RRC_EUTRA_RSTD_InfoList.class;
            case EventTriggerConfig:
                return RRC_EventTriggerConfig.class;
            case EventTriggerConfigInterRAT:
                return RRC_EventTriggerConfigInterRAT.class;
            case FailureInfoRLC_Bearer:
                return RRC_FailureInfoRLC_Bearer.class;
            case FailureInformation:
                return RRC_FailureInformation.class;
            case FailureInformation_IEs:
                return RRC_FailureInformation_IEs.class;
            case FailureReportSCG_EUTRA:
                return RRC_FailureReportSCG_EUTRA.class;
            case FailureReportSCG:
                return RRC_FailureReportSCG.class;
            case FeatureSetCombination:
                return RRC_FeatureSetCombination.class;
            case FeatureSetCombinationId:
                return RRC_FeatureSetCombinationId.class;
            case FeatureSetDownlink:
                return RRC_FeatureSetDownlink.class;
            case FeatureSetDownlinkId:
                return RRC_FeatureSetDownlinkId.class;
            case FeatureSetDownlinkPerCC:
                return RRC_FeatureSetDownlinkPerCC.class;
            case FeatureSetDownlinkPerCC_Id:
                return RRC_FeatureSetDownlinkPerCC_Id.class;
            case FeatureSetDownlink_v1540:
                return RRC_FeatureSetDownlink_v1540.class;
            case FeatureSetEntryIndex:
                return RRC_FeatureSetEntryIndex.class;
            case FeatureSetEUTRA_DownlinkId:
                return RRC_FeatureSetEUTRA_DownlinkId.class;
            case FeatureSetEUTRA_UplinkId:
                return RRC_FeatureSetEUTRA_UplinkId.class;
            case FeatureSet:
                return RRC_FeatureSet.class;
            case FeatureSets:
                return RRC_FeatureSets.class;
            case FeatureSetsPerBand:
                return RRC_FeatureSetsPerBand.class;
            case FeatureSetUplink:
                return RRC_FeatureSetUplink.class;
            case FeatureSetUplinkId:
                return RRC_FeatureSetUplinkId.class;
            case FeatureSetUplinkPerCC:
                return RRC_FeatureSetUplinkPerCC.class;
            case FeatureSetUplinkPerCC_Id:
                return RRC_FeatureSetUplinkPerCC_Id.class;
            case FeatureSetUplinkPerCC_v1540:
                return RRC_FeatureSetUplinkPerCC_v1540.class;
            case FeatureSetUplink_v1540:
                return RRC_FeatureSetUplink_v1540.class;
            case FilterCoefficient:
                return RRC_FilterCoefficient.class;
            case FilterConfig:
                return RRC_FilterConfig.class;
            case FreqBandIndicatorEUTRA:
                return RRC_FreqBandIndicatorEUTRA.class;
            case FreqBandIndicatorNR:
                return RRC_FreqBandIndicatorNR.class;
            case FreqBandInformationEUTRA:
                return RRC_FreqBandInformationEUTRA.class;
            case FreqBandInformation:
                return RRC_FreqBandInformation.class;
            case FreqBandInformationNR:
                return RRC_FreqBandInformationNR.class;
            case FreqBandList:
                return RRC_FreqBandList.class;
            case FreqPriorityEUTRA:
                return RRC_FreqPriorityEUTRA.class;
            case FreqPriorityListEUTRA:
                return RRC_FreqPriorityListEUTRA.class;
            case FreqPriorityListNR:
                return RRC_FreqPriorityListNR.class;
            case FreqPriorityNR:
                return RRC_FreqPriorityNR.class;
            case FreqSeparationClass:
                return RRC_FreqSeparationClass.class;
            case FrequencyInfoDL:
                return RRC_FrequencyInfoDL.class;
            case FrequencyInfoDL_SIB:
                return RRC_FrequencyInfoDL_SIB.class;
            case FrequencyInfoUL:
                return RRC_FrequencyInfoUL.class;
            case FrequencyInfoUL_SIB:
                return RRC_FrequencyInfoUL_SIB.class;
            case FR_Info:
                return RRC_FR_Info.class;
            case FR_InfoList:
                return RRC_FR_InfoList.class;
            case GapConfig:
                return RRC_GapConfig.class;
            case GeneralParametersMRDC_XDD_Diff:
                return RRC_GeneralParametersMRDC_XDD_Diff.class;
            case HandoverCommand:
                return RRC_HandoverCommand.class;
            case HandoverCommand_IEs:
                return RRC_HandoverCommand_IEs.class;
            case HandoverPreparationInformation:
                return RRC_HandoverPreparationInformation.class;
            case HandoverPreparationInformation_IEs:
                return RRC_HandoverPreparationInformation_IEs.class;
            case Hysteresis:
                return RRC_Hysteresis.class;
            case IMS_ParametersCommon:
                return RRC_IMS_ParametersCommon.class;
            case IMS_ParametersFRX_Diff:
                return RRC_IMS_ParametersFRX_Diff.class;
            case IMS_Parameters:
                return RRC_IMS_Parameters.class;
            case InitialUE_Identity:
                return RRC_InitialUE_Identity.class;
            case INT_ConfigurationPerServingCell:
                return RRC_INT_ConfigurationPerServingCell.class;
            case IntegrityProtAlgorithm:
                return RRC_IntegrityProtAlgorithm.class;
            case InterFreqBlackCellList:
                return RRC_InterFreqBlackCellList.class;
            case InterFreqCarrierFreqInfo:
                return RRC_InterFreqCarrierFreqInfo.class;
            case InterFreqCarrierFreqList:
                return RRC_InterFreqCarrierFreqList.class;
            case InterFreqNeighCellInfo:
                return RRC_InterFreqNeighCellInfo.class;
            case InterFreqNeighCellList:
                return RRC_InterFreqNeighCellList.class;
            case InterRAT_Parameters:
                return RRC_InterRAT_Parameters.class;
            case IntraFreqBlackCellList:
                return RRC_IntraFreqBlackCellList.class;
            case IntraFreqNeighCellInfo:
                return RRC_IntraFreqNeighCellInfo.class;
            case IntraFreqNeighCellList:
                return RRC_IntraFreqNeighCellList.class;
            case I_RNTI_Value:
                return RRC_I_RNTI_Value.class;
            case LocationMeasurementIndication:
                return RRC_LocationMeasurementIndication.class;
            case LocationMeasurementIndication_IEs:
                return RRC_LocationMeasurementIndication_IEs.class;
            case LocationMeasurementInfo:
                return RRC_LocationMeasurementInfo.class;
            case LogicalChannelConfig:
                return RRC_LogicalChannelConfig.class;
            case LogicalChannelIdentity:
                return RRC_LogicalChannelIdentity.class;
            case MAC_CellGroupConfig:
                return RRC_MAC_CellGroupConfig.class;
            case MAC_ParametersCommon:
                return RRC_MAC_ParametersCommon.class;
            case MAC_Parameters:
                return RRC_MAC_Parameters.class;
            case MAC_ParametersXDD_Diff:
                return RRC_MAC_ParametersXDD_Diff.class;
            case MasterKeyUpdate:
                return RRC_MasterKeyUpdate.class;
            case MCC:
                return RRC_MCC.class;
            case MCC_MNC_Digit:
                return RRC_MCC_MNC_Digit.class;
            case MeasAndMobParametersCommon:
                return RRC_MeasAndMobParametersCommon.class;
            case MeasAndMobParametersFRX_Diff:
                return RRC_MeasAndMobParametersFRX_Diff.class;
            case MeasAndMobParameters:
                return RRC_MeasAndMobParameters.class;
            case MeasAndMobParametersMRDC_Common:
                return RRC_MeasAndMobParametersMRDC_Common.class;
            case MeasAndMobParametersMRDC_FRX_Diff:
                return RRC_MeasAndMobParametersMRDC_FRX_Diff.class;
            case MeasAndMobParametersMRDC:
                return RRC_MeasAndMobParametersMRDC.class;
            case MeasAndMobParametersMRDC_v1560:
                return RRC_MeasAndMobParametersMRDC_v1560.class;
            case MeasAndMobParametersMRDC_XDD_Diff:
                return RRC_MeasAndMobParametersMRDC_XDD_Diff.class;
            case MeasAndMobParametersMRDC_XDD_Diff_v1560:
                return RRC_MeasAndMobParametersMRDC_XDD_Diff_v1560.class;
            case MeasAndMobParametersXDD_Diff:
                return RRC_MeasAndMobParametersXDD_Diff.class;
            case MeasConfig:
                return RRC_MeasConfig.class;
            case MeasConfigMN:
                return RRC_MeasConfigMN.class;
            case MeasConfigSN:
                return RRC_MeasConfigSN.class;
            case MeasGapConfig:
                return RRC_MeasGapConfig.class;
            case MeasGapSharingConfig:
                return RRC_MeasGapSharingConfig.class;
            case MeasGapSharingScheme:
                return RRC_MeasGapSharingScheme.class;
            case MeasId:
                return RRC_MeasId.class;
            case MeasIdToAddMod:
                return RRC_MeasIdToAddMod.class;
            case MeasIdToAddModList:
                return RRC_MeasIdToAddModList.class;
            case MeasIdToRemoveList:
                return RRC_MeasIdToRemoveList.class;
            case MeasObjectEUTRA:
                return RRC_MeasObjectEUTRA.class;
            case MeasObjectId:
                return RRC_MeasObjectId.class;
            case MeasObjectNR:
                return RRC_MeasObjectNR.class;
            case MeasObjectToAddMod:
                return RRC_MeasObjectToAddMod.class;
            case MeasObjectToAddModList:
                return RRC_MeasObjectToAddModList.class;
            case MeasObjectToRemoveList:
                return RRC_MeasObjectToRemoveList.class;
            case MeasQuantityResultsEUTRA:
                return RRC_MeasQuantityResultsEUTRA.class;
            case MeasQuantityResults:
                return RRC_MeasQuantityResults.class;
            case MeasReportQuantity:
                return RRC_MeasReportQuantity.class;
            case MeasResult2EUTRA:
                return RRC_MeasResult2EUTRA.class;
            case MeasResult2NR:
                return RRC_MeasResult2NR.class;
            case MeasResultCellListSFTD_EUTRA:
                return RRC_MeasResultCellListSFTD_EUTRA.class;
            case MeasResultCellListSFTD_NR:
                return RRC_MeasResultCellListSFTD_NR.class;
            case MeasResultCellSFTD_NR:
                return RRC_MeasResultCellSFTD_NR.class;
            case MeasResultEUTRA:
                return RRC_MeasResultEUTRA.class;
            case MeasResultFreqListFailMRDC:
                return RRC_MeasResultFreqListFailMRDC.class;
            case MeasResultFreqList:
                return RRC_MeasResultFreqList.class;
            case MeasResultList2NR:
                return RRC_MeasResultList2NR.class;
            case MeasResultListEUTRA:
                return RRC_MeasResultListEUTRA.class;
            case MeasResultListNR:
                return RRC_MeasResultListNR.class;
            case MeasResultNR:
                return RRC_MeasResultNR.class;
            case MeasResultSCG_Failure:
                return RRC_MeasResultSCG_Failure.class;
            case MeasResultServFreqListEUTRA_SCG:
                return RRC_MeasResultServFreqListEUTRA_SCG.class;
            case MeasResultServFreqListNR_SCG:
                return RRC_MeasResultServFreqListNR_SCG.class;
            case MeasResultServMO:
                return RRC_MeasResultServMO.class;
            case MeasResultServMOList:
                return RRC_MeasResultServMOList.class;
            case MeasResultSFTD_EUTRA:
                return RRC_MeasResultSFTD_EUTRA.class;
            case MeasResults:
                return RRC_MeasResults.class;
            case MeasTiming:
                return RRC_MeasTiming.class;
            case MeasTimingList:
                return RRC_MeasTimingList.class;
            case MeasTriggerQuantityEUTRA:
                return RRC_MeasTriggerQuantityEUTRA.class;
            case MeasTriggerQuantity:
                return RRC_MeasTriggerQuantity.class;
            case MeasTriggerQuantityOffset:
                return RRC_MeasTriggerQuantityOffset.class;
            case MeasurementReport:
                return RRC_MeasurementReport.class;
            case MeasurementReport_IEs:
                return RRC_MeasurementReport_IEs.class;
            case MeasurementTimingConfiguration:
                return RRC_MeasurementTimingConfiguration.class;
            case MeasurementTimingConfiguration_IEs:
                return RRC_MeasurementTimingConfiguration_IEs.class;
            case MeasurementTimingConfiguration_v1550_IEs:
                return RRC_MeasurementTimingConfiguration_v1550_IEs.class;
            case MIB:
                return RRC_MIB.class;
            case MIMO_LayersDL:
                return RRC_MIMO_LayersDL.class;
            case MIMO_LayersUL:
                return RRC_MIMO_LayersUL.class;
            case MIMO_ParametersPerBand:
                return RRC_MIMO_ParametersPerBand.class;
            case MNC:
                return RRC_MNC.class;
            case MobilityFromNRCommand:
                return RRC_MobilityFromNRCommand.class;
            case MobilityFromNRCommand_IEs:
                return RRC_MobilityFromNRCommand_IEs.class;
            case MobilityStateParameters:
                return RRC_MobilityStateParameters.class;
            case ModulationOrder:
                return RRC_ModulationOrder.class;
            case MRDC_AssistanceInfo:
                return RRC_MRDC_AssistanceInfo.class;
            case MRDC_Parameters:
                return RRC_MRDC_Parameters.class;
            case MRDC_SecondaryCellGroupConfig:
                return RRC_MRDC_SecondaryCellGroupConfig.class;
            case MultiBandInfoListEUTRA:
                return RRC_MultiBandInfoListEUTRA.class;
            case MultiFrequencyBandListNR:
                return RRC_MultiFrequencyBandListNR.class;
            case MultiFrequencyBandListNR_SIB:
                return RRC_MultiFrequencyBandListNR_SIB.class;
            case NAICS_Capability_Entry:
                return RRC_NAICS_Capability_Entry.class;
            case NextHopChainingCount:
                return RRC_NextHopChainingCount.class;
            case NG_5G_S_TMSI:
                return RRC_NG_5G_S_TMSI.class;
            case NRDC_Parameters:
                return RRC_NRDC_Parameters.class;
            case NR_FreqInfo:
                return RRC_NR_FreqInfo.class;
            case NR_MultiBandInfo:
                return RRC_NR_MultiBandInfo.class;
            case NR_NS_PmaxList:
                return RRC_NR_NS_PmaxList.class;
            case NR_NS_PmaxValue:
                return RRC_NR_NS_PmaxValue.class;
            case NR_RS_Type:
                return RRC_NR_RS_Type.class;
            case NumberOfCarriers:
                return RRC_NumberOfCarriers.class;
            case NZP_CSI_RS_Resource:
                return RRC_NZP_CSI_RS_Resource.class;
            case NZP_CSI_RS_ResourceId:
                return RRC_NZP_CSI_RS_ResourceId.class;
            case NZP_CSI_RS_ResourceSet:
                return RRC_NZP_CSI_RS_ResourceSet.class;
            case NZP_CSI_RS_ResourceSetId:
                return RRC_NZP_CSI_RS_ResourceSetId.class;
            case OtherConfig:
                return RRC_OtherConfig.class;
            case OtherConfig_v1540:
                return RRC_OtherConfig_v1540.class;
            case OverheatingAssistanceConfig:
                return RRC_OverheatingAssistanceConfig.class;
            case OverheatingAssistance:
                return RRC_OverheatingAssistance.class;
            case P0_PUCCH:
                return RRC_P0_PUCCH.class;
            case P0_PUCCH_Id:
                return RRC_P0_PUCCH_Id.class;
            case P0_PUSCH_AlphaSet:
                return RRC_P0_PUSCH_AlphaSet.class;
            case P0_PUSCH_AlphaSetId:
                return RRC_P0_PUSCH_AlphaSetId.class;
            case PagingCycle:
                return RRC_PagingCycle.class;
            case Paging:
                return RRC_Paging.class;
            case PagingRecord:
                return RRC_PagingRecord.class;
            case PagingRecordList:
                return RRC_PagingRecordList.class;
            case PagingUE_Identity:
                return RRC_PagingUE_Identity.class;
            case PCCH_Config:
                return RRC_PCCH_Config.class;
            case PCCH_Message:
                return RRC_PCCH_Message.class;
            case PCCH_MessageType:
                return RRC_PCCH_MessageType.class;
            case PCI_List:
                return RRC_PCI_List.class;
            case PCI_RangeElement:
                return RRC_PCI_RangeElement.class;
            case PCI_Range:
                return RRC_PCI_Range.class;
            case PCI_RangeIndex:
                return RRC_PCI_RangeIndex.class;
            case PCI_RangeIndexList:
                return RRC_PCI_RangeIndexList.class;
            case PDCCH_BlindDetection:
                return RRC_PDCCH_BlindDetection.class;
            case PDCCH_ConfigCommon:
                return RRC_PDCCH_ConfigCommon.class;
            case PDCCH_Config:
                return RRC_PDCCH_Config.class;
            case PDCCH_ConfigSIB1:
                return RRC_PDCCH_ConfigSIB1.class;
            case PDCCH_ServingCellConfig:
                return RRC_PDCCH_ServingCellConfig.class;
            case PDCP_Config:
                return RRC_PDCP_Config.class;
            case PDCP_Parameters:
                return RRC_PDCP_Parameters.class;
            case PDCP_ParametersMRDC:
                return RRC_PDCP_ParametersMRDC.class;
            case PDSCH_CodeBlockGroupTransmission:
                return RRC_PDSCH_CodeBlockGroupTransmission.class;
            case PDSCH_ConfigCommon:
                return RRC_PDSCH_ConfigCommon.class;
            case PDSCH_Config:
                return RRC_PDSCH_Config.class;
            case PDSCH_ServingCellConfig:
                return RRC_PDSCH_ServingCellConfig.class;
            case PDSCH_TimeDomainResourceAllocation:
                return RRC_PDSCH_TimeDomainResourceAllocation.class;
            case PDSCH_TimeDomainResourceAllocationList:
                return RRC_PDSCH_TimeDomainResourceAllocationList.class;
            case PDU_SessionID:
                return RRC_PDU_SessionID.class;
            case PeriodicalReportConfig:
                return RRC_PeriodicalReportConfig.class;
            case PeriodicalReportConfigInterRAT:
                return RRC_PeriodicalReportConfigInterRAT.class;
            case PeriodicRNAU_TimerValue:
                return RRC_PeriodicRNAU_TimerValue.class;
            case PH_InfoMCG:
                return RRC_PH_InfoMCG.class;
            case PH_InfoSCG:
                return RRC_PH_InfoSCG.class;
            case PHR_Config:
                return RRC_PHR_Config.class;
            case PH_TypeListMCG:
                return RRC_PH_TypeListMCG.class;
            case PH_TypeListSCG:
                return RRC_PH_TypeListSCG.class;
            case PH_UplinkCarrierMCG:
                return RRC_PH_UplinkCarrierMCG.class;
            case PH_UplinkCarrierSCG:
                return RRC_PH_UplinkCarrierSCG.class;
            case Phy_ParametersCommon:
                return RRC_Phy_ParametersCommon.class;
            case Phy_ParametersFR1:
                return RRC_Phy_ParametersFR1.class;
            case Phy_ParametersFR2:
                return RRC_Phy_ParametersFR2.class;
            case Phy_ParametersFRX_Diff:
                return RRC_Phy_ParametersFRX_Diff.class;
            case Phy_Parameters:
                return RRC_Phy_Parameters.class;
            case Phy_ParametersMRDC:
                return RRC_Phy_ParametersMRDC.class;
            case Phy_ParametersXDD_Diff:
                return RRC_Phy_ParametersXDD_Diff.class;
            case PhysCellId:
                return RRC_PhysCellId.class;
            case PhysicalCellGroupConfig:
                return RRC_PhysicalCellGroupConfig.class;
            case PLMN_Identity_EUTRA_5GC:
                return RRC_PLMN_Identity_EUTRA_5GC.class;
            case PLMN_Identity:
                return RRC_PLMN_Identity.class;
            case PLMN_IdentityInfo:
                return RRC_PLMN_IdentityInfo.class;
            case PLMN_IdentityInfoList:
                return RRC_PLMN_IdentityInfoList.class;
            case PLMN_IdentityList_EUTRA_5GC:
                return RRC_PLMN_IdentityList_EUTRA_5GC.class;
            case PLMN_IdentityList_EUTRA_EPC:
                return RRC_PLMN_IdentityList_EUTRA_EPC.class;
            case PLMN_RAN_AreaCell:
                return RRC_PLMN_RAN_AreaCell.class;
            case PLMN_RAN_AreaCellList:
                return RRC_PLMN_RAN_AreaCellList.class;
            case PLMN_RAN_AreaConfig:
                return RRC_PLMN_RAN_AreaConfig.class;
            case PLMN_RAN_AreaConfigList:
                return RRC_PLMN_RAN_AreaConfigList.class;
            case P_Max:
                return RRC_P_Max.class;
            case PollByte:
                return RRC_PollByte.class;
            case PollPDU:
                return RRC_PollPDU.class;
            case PortIndex2:
                return RRC_PortIndex2.class;
            case PortIndex4:
                return RRC_PortIndex4.class;
            case PortIndex8:
                return RRC_PortIndex8.class;
            case PortIndexFor8Ranks:
                return RRC_PortIndexFor8Ranks.class;
            case PRACH_ResourceDedicatedBFR:
                return RRC_PRACH_ResourceDedicatedBFR.class;
            case PRB_Id:
                return RRC_PRB_Id.class;
            case ProcessingParameters:
                return RRC_ProcessingParameters.class;
            case PTRS_DensityRecommendationDL:
                return RRC_PTRS_DensityRecommendationDL.class;
            case PTRS_DensityRecommendationUL:
                return RRC_PTRS_DensityRecommendationUL.class;
            case PTRS_DownlinkConfig:
                return RRC_PTRS_DownlinkConfig.class;
            case PTRS_UplinkConfig:
                return RRC_PTRS_UplinkConfig.class;
            case PUCCH_ConfigCommon:
                return RRC_PUCCH_ConfigCommon.class;
            case PUCCH_Config:
                return RRC_PUCCH_Config.class;
            case PUCCH_CSI_Resource:
                return RRC_PUCCH_CSI_Resource.class;
            case PUCCH_format0:
                return RRC_PUCCH_format0.class;
            case PUCCH_format1:
                return RRC_PUCCH_format1.class;
            case PUCCH_format2:
                return RRC_PUCCH_format2.class;
            case PUCCH_format3:
                return RRC_PUCCH_format3.class;
            case PUCCH_format4:
                return RRC_PUCCH_format4.class;
            case PUCCH_FormatConfig:
                return RRC_PUCCH_FormatConfig.class;
            case PUCCH_MaxCodeRate:
                return RRC_PUCCH_MaxCodeRate.class;
            case PUCCH_PathlossReferenceRS:
                return RRC_PUCCH_PathlossReferenceRS.class;
            case PUCCH_PathlossReferenceRS_Id:
                return RRC_PUCCH_PathlossReferenceRS_Id.class;
            case PUCCH_PowerControl:
                return RRC_PUCCH_PowerControl.class;
            case PUCCH_Resource:
                return RRC_PUCCH_Resource.class;
            case PUCCH_ResourceId:
                return RRC_PUCCH_ResourceId.class;
            case PUCCH_ResourceSet:
                return RRC_PUCCH_ResourceSet.class;
            case PUCCH_ResourceSetId:
                return RRC_PUCCH_ResourceSetId.class;
            case PUCCH_SpatialRelationInfo:
                return RRC_PUCCH_SpatialRelationInfo.class;
            case PUCCH_SpatialRelationInfoId:
                return RRC_PUCCH_SpatialRelationInfoId.class;
            case PUCCH_TPC_CommandConfig:
                return RRC_PUCCH_TPC_CommandConfig.class;
            case PUSCH_CodeBlockGroupTransmission:
                return RRC_PUSCH_CodeBlockGroupTransmission.class;
            case PUSCH_ConfigCommon:
                return RRC_PUSCH_ConfigCommon.class;
            case PUSCH_Config:
                return RRC_PUSCH_Config.class;
            case PUSCH_PathlossReferenceRS:
                return RRC_PUSCH_PathlossReferenceRS.class;
            case PUSCH_PathlossReferenceRS_Id:
                return RRC_PUSCH_PathlossReferenceRS_Id.class;
            case PUSCH_PowerControl:
                return RRC_PUSCH_PowerControl.class;
            case PUSCH_ServingCellConfig:
                return RRC_PUSCH_ServingCellConfig.class;
            case PUSCH_TimeDomainResourceAllocation:
                return RRC_PUSCH_TimeDomainResourceAllocation.class;
            case PUSCH_TimeDomainResourceAllocationList:
                return RRC_PUSCH_TimeDomainResourceAllocationList.class;
            case PUSCH_TPC_CommandConfig:
                return RRC_PUSCH_TPC_CommandConfig.class;
            case QCL_Info:
                return RRC_QCL_Info.class;
            case QFI:
                return RRC_QFI.class;
            case Q_OffsetRange:
                return RRC_Q_OffsetRange.class;
            case Q_OffsetRangeList:
                return RRC_Q_OffsetRangeList.class;
            case Q_QualMin:
                return RRC_Q_QualMin.class;
            case Q_RxLevMin:
                return RRC_Q_RxLevMin.class;
            case QuantityConfig:
                return RRC_QuantityConfig.class;
            case QuantityConfigNR:
                return RRC_QuantityConfigNR.class;
            case QuantityConfigRS:
                return RRC_QuantityConfigRS.class;
            case RACH_ConfigCommon:
                return RRC_RACH_ConfigCommon.class;
            case RACH_ConfigDedicated:
                return RRC_RACH_ConfigDedicated.class;
            case RACH_ConfigGeneric:
                return RRC_RACH_ConfigGeneric.class;
            case RadioBearerConfig:
                return RRC_RadioBearerConfig.class;
            case RadioLinkMonitoringConfig:
                return RRC_RadioLinkMonitoringConfig.class;
            case RadioLinkMonitoringRS:
                return RRC_RadioLinkMonitoringRS.class;
            case RadioLinkMonitoringRS_Id:
                return RRC_RadioLinkMonitoringRS_Id.class;
            case RAN_AreaCode:
                return RRC_RAN_AreaCode.class;
            case RAN_AreaConfig:
                return RRC_RAN_AreaConfig.class;
            case RangeToBestCell:
                return RRC_RangeToBestCell.class;
            case RAN_NotificationAreaInfo:
                return RRC_RAN_NotificationAreaInfo.class;
            case RA_Prioritization:
                return RRC_RA_Prioritization.class;
            case RateMatchPatternGroup:
                return RRC_RateMatchPatternGroup.class;
            case RateMatchPattern:
                return RRC_RateMatchPattern.class;
            case RateMatchPatternId:
                return RRC_RateMatchPatternId.class;
            case RateMatchPatternLTE_CRS:
                return RRC_RateMatchPatternLTE_CRS.class;
            case RAT_Type:
                return RRC_RAT_Type.class;
            case ReconfigurationWithSync:
                return RRC_ReconfigurationWithSync.class;
            case RedirectedCarrierInfo_EUTRA:
                return RRC_RedirectedCarrierInfo_EUTRA.class;
            case RedirectedCarrierInfo:
                return RRC_RedirectedCarrierInfo.class;
            case ReducedAggregatedBandwidth:
                return RRC_ReducedAggregatedBandwidth.class;
            case ReestablishmentCause:
                return RRC_ReestablishmentCause.class;
            case ReestablishmentInfo:
                return RRC_ReestablishmentInfo.class;
            case ReestabNCellInfo:
                return RRC_ReestabNCellInfo.class;
            case ReestabNCellInfoList:
                return RRC_ReestabNCellInfoList.class;
            case ReestabUE_Identity:
                return RRC_ReestabUE_Identity.class;
            case ReferenceSignalConfig:
                return RRC_ReferenceSignalConfig.class;
            case RegisteredAMF:
                return RRC_RegisteredAMF.class;
            case RejectWaitTime:
                return RRC_RejectWaitTime.class;
            case ReportCGI_EUTRA:
                return RRC_ReportCGI_EUTRA.class;
            case ReportCGI:
                return RRC_ReportCGI.class;
            case ReportConfigId:
                return RRC_ReportConfigId.class;
            case ReportConfigInterRAT:
                return RRC_ReportConfigInterRAT.class;
            case ReportConfigNR:
                return RRC_ReportConfigNR.class;
            case ReportConfigToAddMod:
                return RRC_ReportConfigToAddMod.class;
            case ReportConfigToAddModList:
                return RRC_ReportConfigToAddModList.class;
            case ReportConfigToRemoveList:
                return RRC_ReportConfigToRemoveList.class;
            case ReportInterval:
                return RRC_ReportInterval.class;
            case ReportSFTD_EUTRA:
                return RRC_ReportSFTD_EUTRA.class;
            case ReportSFTD_NR:
                return RRC_ReportSFTD_NR.class;
            case ReselectionThreshold:
                return RRC_ReselectionThreshold.class;
            case ReselectionThresholdQ:
                return RRC_ReselectionThresholdQ.class;
            case ResultsPerCSI_RS_Index:
                return RRC_ResultsPerCSI_RS_Index.class;
            case ResultsPerCSI_RS_IndexList:
                return RRC_ResultsPerCSI_RS_IndexList.class;
            case ResultsPerSSB_Index:
                return RRC_ResultsPerSSB_Index.class;
            case ResultsPerSSB_IndexList:
                return RRC_ResultsPerSSB_IndexList.class;
            case ResumeCause:
                return RRC_ResumeCause.class;
            case RF_Parameters:
                return RRC_RF_Parameters.class;
            case RF_ParametersMRDC:
                return RRC_RF_ParametersMRDC.class;
            case RLC_BearerConfig:
                return RRC_RLC_BearerConfig.class;
            case RLC_Config:
                return RRC_RLC_Config.class;
            case RLC_Parameters:
                return RRC_RLC_Parameters.class;
            case RLF_TimersAndConstants:
                return RRC_RLF_TimersAndConstants.class;
            case RNTI_Value:
                return RRC_RNTI_Value.class;
            case RRCReconfigurationComplete:
                return RRC_RRCReconfigurationComplete.class;
            case RRCReconfigurationComplete_IEs:
                return RRC_RRCReconfigurationComplete_IEs.class;
            case RRCReconfigurationComplete_v1530_IEs:
                return RRC_RRCReconfigurationComplete_v1530_IEs.class;
            case RRCReconfigurationComplete_v1560_IEs:
                return RRC_RRCReconfigurationComplete_v1560_IEs.class;
            case RRCReconfiguration:
                return RRC_RRCReconfiguration.class;
            case RRCReconfiguration_IEs:
                return RRC_RRCReconfiguration_IEs.class;
            case RRCReconfiguration_v1530_IEs:
                return RRC_RRCReconfiguration_v1530_IEs.class;
            case RRCReconfiguration_v1540_IEs:
                return RRC_RRCReconfiguration_v1540_IEs.class;
            case RRCReconfiguration_v1560_IEs:
                return RRC_RRCReconfiguration_v1560_IEs.class;
            case RRCReestablishmentComplete:
                return RRC_RRCReestablishmentComplete.class;
            case RRCReestablishmentComplete_IEs:
                return RRC_RRCReestablishmentComplete_IEs.class;
            case RRCReestablishment:
                return RRC_RRCReestablishment.class;
            case RRCReestablishment_IEs:
                return RRC_RRCReestablishment_IEs.class;
            case RRCReestablishmentRequest:
                return RRC_RRCReestablishmentRequest.class;
            case RRCReestablishmentRequest_IEs:
                return RRC_RRCReestablishmentRequest_IEs.class;
            case RRCReject:
                return RRC_RRCReject.class;
            case RRCReject_IEs:
                return RRC_RRCReject_IEs.class;
            case RRCRelease:
                return RRC_RRCRelease.class;
            case RRCRelease_IEs:
                return RRC_RRCRelease_IEs.class;
            case RRCRelease_v1540_IEs:
                return RRC_RRCRelease_v1540_IEs.class;
            case RRCResumeComplete:
                return RRC_RRCResumeComplete.class;
            case RRCResumeComplete_IEs:
                return RRC_RRCResumeComplete_IEs.class;
            case RRCResume:
                return RRC_RRCResume.class;
            case RRCResume_IEs:
                return RRC_RRCResume_IEs.class;
            case RRCResumeRequest1:
                return RRC_RRCResumeRequest1.class;
            case RRCResumeRequest1_IEs:
                return RRC_RRCResumeRequest1_IEs.class;
            case RRCResumeRequest:
                return RRC_RRCResumeRequest.class;
            case RRCResumeRequest_IEs:
                return RRC_RRCResumeRequest_IEs.class;
            case RRCResume_v1560_IEs:
                return RRC_RRCResume_v1560_IEs.class;
            case RRCSetupComplete:
                return RRC_RRCSetupComplete.class;
            case RRCSetupComplete_IEs:
                return RRC_RRCSetupComplete_IEs.class;
            case RRCSetup:
                return RRC_RRCSetup.class;
            case RRCSetup_IEs:
                return RRC_RRCSetup_IEs.class;
            case RRCSetupRequest:
                return RRC_RRCSetupRequest.class;
            case RRCSetupRequest_IEs:
                return RRC_RRCSetupRequest_IEs.class;
            case RRCSystemInfoRequest:
                return RRC_RRCSystemInfoRequest.class;
            case RRCSystemInfoRequest_r15_IEs:
                return RRC_RRCSystemInfoRequest_r15_IEs.class;
            case RRC_TransactionIdentifier:
                return RRC_RRC_TransactionIdentifier.class;
            case RRM_Config:
                return RRC_RRM_Config.class;
            case RSRP_RangeEUTRA:
                return RRC_RSRP_RangeEUTRA.class;
            case RSRP_Range:
                return RRC_RSRP_Range.class;
            case RSRQ_RangeEUTRA:
                return RRC_RSRQ_RangeEUTRA.class;
            case RSRQ_Range:
                return RRC_RSRQ_Range.class;
            case SCellConfig:
                return RRC_SCellConfig.class;
            case SCellIndex:
                return RRC_SCellIndex.class;
            case SCGFailureInformationEUTRA:
                return RRC_SCGFailureInformationEUTRA.class;
            case SCGFailureInformationEUTRA_IEs:
                return RRC_SCGFailureInformationEUTRA_IEs.class;
            case SCGFailureInformation:
                return RRC_SCGFailureInformation.class;
            case SCGFailureInformation_IEs:
                return RRC_SCGFailureInformation_IEs.class;
            case SchedulingInfo:
                return RRC_SchedulingInfo.class;
            case SchedulingRequestConfig:
                return RRC_SchedulingRequestConfig.class;
            case SchedulingRequestId:
                return RRC_SchedulingRequestId.class;
            case SchedulingRequestResourceConfig:
                return RRC_SchedulingRequestResourceConfig.class;
            case SchedulingRequestResourceId:
                return RRC_SchedulingRequestResourceId.class;
            case SchedulingRequestToAddMod:
                return RRC_SchedulingRequestToAddMod.class;
            case ScramblingId:
                return RRC_ScramblingId.class;
            case SCS_SpecificCarrier:
                return RRC_SCS_SpecificCarrier.class;
            case SDAP_Config:
                return RRC_SDAP_Config.class;
            case SDAP_Parameters:
                return RRC_SDAP_Parameters.class;
            case SearchSpace:
                return RRC_SearchSpace.class;
            case SearchSpaceId:
                return RRC_SearchSpaceId.class;
            case SearchSpaceZero:
                return RRC_SearchSpaceZero.class;
            case SecurityAlgorithmConfig:
                return RRC_SecurityAlgorithmConfig.class;
            case SecurityConfig:
                return RRC_SecurityConfig.class;
            case SecurityConfigSMC:
                return RRC_SecurityConfigSMC.class;
            case SecurityModeCommand:
                return RRC_SecurityModeCommand.class;
            case SecurityModeCommand_IEs:
                return RRC_SecurityModeCommand_IEs.class;
            case SecurityModeComplete:
                return RRC_SecurityModeComplete.class;
            case SecurityModeComplete_IEs:
                return RRC_SecurityModeComplete_IEs.class;
            case SecurityModeFailure:
                return RRC_SecurityModeFailure.class;
            case SecurityModeFailure_IEs:
                return RRC_SecurityModeFailure_IEs.class;
            case ServCellIndex:
                return RRC_ServCellIndex.class;
            case ServingCellConfigCommon:
                return RRC_ServingCellConfigCommon.class;
            case ServingCellConfigCommonSIB:
                return RRC_ServingCellConfigCommonSIB.class;
            case ServingCellConfig:
                return RRC_ServingCellConfig.class;
            case ShortI_RNTI_Value:
                return RRC_ShortI_RNTI_Value.class;
            case ShortMAC_I:
                return RRC_ShortMAC_I.class;
            case SIB1:
                return RRC_SIB1.class;
            case SIB2:
                return RRC_SIB2.class;
            case SIB3:
                return RRC_SIB3.class;
            case SIB4:
                return RRC_SIB4.class;
            case SIB5:
                return RRC_SIB5.class;
            case SIB6:
                return RRC_SIB6.class;
            case SIB7:
                return RRC_SIB7.class;
            case SIB8:
                return RRC_SIB8.class;
            case SIB9:
                return RRC_SIB9.class;
            case SIB_Mapping:
                return RRC_SIB_Mapping.class;
            case SIB_TypeInfo:
                return RRC_SIB_TypeInfo.class;
            case SINR_RangeEUTRA:
                return RRC_SINR_RangeEUTRA.class;
            case SINR_Range:
                return RRC_SINR_Range.class;
            case SI_RequestConfig:
                return RRC_SI_RequestConfig.class;
            case SI_RequestResources:
                return RRC_SI_RequestResources.class;
            case SI_SchedulingInfo:
                return RRC_SI_SchedulingInfo.class;
            case SK_Counter:
                return RRC_SK_Counter.class;
            case SlotFormatCombination:
                return RRC_SlotFormatCombination.class;
            case SlotFormatCombinationId:
                return RRC_SlotFormatCombinationId.class;
            case SlotFormatCombinationsPerCell:
                return RRC_SlotFormatCombinationsPerCell.class;
            case SlotFormatIndicator:
                return RRC_SlotFormatIndicator.class;
            case SN_FieldLengthAM:
                return RRC_SN_FieldLengthAM.class;
            case SN_FieldLengthUM:
                return RRC_SN_FieldLengthUM.class;
            case S_NSSAI:
                return RRC_S_NSSAI.class;
            case SpatialRelations:
                return RRC_SpatialRelations.class;
            case SpCellConfig:
                return RRC_SpCellConfig.class;
            case SpeedStateScaleFactors:
                return RRC_SpeedStateScaleFactors.class;
            case SPS_Config:
                return RRC_SPS_Config.class;
            case SRB_Identity:
                return RRC_SRB_Identity.class;
            case SRB_ToAddMod:
                return RRC_SRB_ToAddMod.class;
            case SRB_ToAddModList:
                return RRC_SRB_ToAddModList.class;
            case SRI_PUSCH_PowerControl:
                return RRC_SRI_PUSCH_PowerControl.class;
            case SRI_PUSCH_PowerControlId:
                return RRC_SRI_PUSCH_PowerControlId.class;
            case SRS_CarrierSwitching:
                return RRC_SRS_CarrierSwitching.class;
            case SRS_CC_SetIndex:
                return RRC_SRS_CC_SetIndex.class;
            case SRS_Config:
                return RRC_SRS_Config.class;
            case SRS_PeriodicityAndOffset:
                return RRC_SRS_PeriodicityAndOffset.class;
            case SRS_Resource:
                return RRC_SRS_Resource.class;
            case SRS_ResourceId:
                return RRC_SRS_ResourceId.class;
            case SRS_ResourceSet:
                return RRC_SRS_ResourceSet.class;
            case SRS_ResourceSetId:
                return RRC_SRS_ResourceSetId.class;
            case SRS_Resources:
                return RRC_SRS_Resources.class;
            case SRS_SpatialRelationInfo:
                return RRC_SRS_SpatialRelationInfo.class;
            case SRS_SwitchingTimeEUTRA:
                return RRC_SRS_SwitchingTimeEUTRA.class;
            case SRS_SwitchingTimeNR:
                return RRC_SRS_SwitchingTimeNR.class;
            case SRS_TPC_CommandConfig:
                return RRC_SRS_TPC_CommandConfig.class;
            case SRS_TPC_PDCCH_Config:
                return RRC_SRS_TPC_PDCCH_Config.class;
            case SSB_ConfigMobility:
                return RRC_SSB_ConfigMobility.class;
            case SSB_Index:
                return RRC_SSB_Index.class;
            case SSB_MTC2:
                return RRC_SSB_MTC2.class;
            case SSB_MTC:
                return RRC_SSB_MTC.class;
            case SSB_ToMeasure:
                return RRC_SSB_ToMeasure.class;
            case SS_RSSI_Measurement:
                return RRC_SS_RSSI_Measurement.class;
            case SubcarrierSpacing:
                return RRC_SubcarrierSpacing.class;
            case SupportedBandwidth:
                return RRC_SupportedBandwidth.class;
            case SupportedCSI_RS_Resource:
                return RRC_SupportedCSI_RS_Resource.class;
            case SuspendConfig:
                return RRC_SuspendConfig.class;
            case SystemInformation:
                return RRC_SystemInformation.class;
            case SystemInformation_IEs:
                return RRC_SystemInformation_IEs.class;
            case TAG_Config:
                return RRC_TAG_Config.class;
            case TAG:
                return RRC_TAG.class;
            case TAG_Id:
                return RRC_TAG_Id.class;
            case TCI_State:
                return RRC_TCI_State.class;
            case TCI_StateId:
                return RRC_TCI_StateId.class;
            case TDD_UL_DL_ConfigCommon:
                return RRC_TDD_UL_DL_ConfigCommon.class;
            case TDD_UL_DL_ConfigDedicated:
                return RRC_TDD_UL_DL_ConfigDedicated.class;
            case TDD_UL_DL_Pattern:
                return RRC_TDD_UL_DL_Pattern.class;
            case TDD_UL_DL_SlotConfig:
                return RRC_TDD_UL_DL_SlotConfig.class;
            case TDD_UL_DL_SlotIndex:
                return RRC_TDD_UL_DL_SlotIndex.class;
            case ThresholdNR:
                return RRC_ThresholdNR.class;
            case TimeAlignmentTimer:
                return RRC_TimeAlignmentTimer.class;
            case TimeToTrigger:
                return RRC_TimeToTrigger.class;
            case T_PollRetransmit:
                return RRC_T_PollRetransmit.class;
            case TrackingAreaCode:
                return RRC_TrackingAreaCode.class;
            case T_Reassembly:
                return RRC_T_Reassembly.class;
            case T_Reselection:
                return RRC_T_Reselection.class;
            case T_StatusProhibit:
                return RRC_T_StatusProhibit.class;
            case UAC_AccessCategory1_SelectionAssistanceInfo:
                return RRC_UAC_AccessCategory1_SelectionAssistanceInfo.class;
            case UAC_BarringInfoSet:
                return RRC_UAC_BarringInfoSet.class;
            case UAC_BarringInfoSetIndex:
                return RRC_UAC_BarringInfoSetIndex.class;
            case UAC_BarringInfoSetList:
                return RRC_UAC_BarringInfoSetList.class;
            case UAC_BarringPerCat:
                return RRC_UAC_BarringPerCat.class;
            case UAC_BarringPerCatList:
                return RRC_UAC_BarringPerCatList.class;
            case UAC_BarringPerPLMN:
                return RRC_UAC_BarringPerPLMN.class;
            case UAC_BarringPerPLMN_List:
                return RRC_UAC_BarringPerPLMN_List.class;
            case UCI_OnPUSCH:
                return RRC_UCI_OnPUSCH.class;
            case UEAssistanceInformation:
                return RRC_UEAssistanceInformation.class;
            case UEAssistanceInformation_IEs:
                return RRC_UEAssistanceInformation_IEs.class;
            case UEAssistanceInformation_v1540_IEs:
                return RRC_UEAssistanceInformation_v1540_IEs.class;
            case UECapabilityEnquiry:
                return RRC_UECapabilityEnquiry.class;
            case UECapabilityEnquiry_IEs:
                return RRC_UECapabilityEnquiry_IEs.class;
            case UECapabilityEnquiry_v1560_IEs:
                return RRC_UECapabilityEnquiry_v1560_IEs.class;
            case UECapabilityInformation:
                return RRC_UECapabilityInformation.class;
            case UECapabilityInformation_IEs:
                return RRC_UECapabilityInformation_IEs.class;
            case UE_CapabilityRAT_Container:
                return RRC_UE_CapabilityRAT_Container.class;
            case UE_CapabilityRAT_ContainerList:
                return RRC_UE_CapabilityRAT_ContainerList.class;
            case UE_CapabilityRAT_Request:
                return RRC_UE_CapabilityRAT_Request.class;
            case UE_CapabilityRAT_RequestList:
                return RRC_UE_CapabilityRAT_RequestList.class;
            case UE_CapabilityRequestFilterCommon:
                return RRC_UE_CapabilityRequestFilterCommon.class;
            case UE_CapabilityRequestFilterNR:
                return RRC_UE_CapabilityRequestFilterNR.class;
            case UE_CapabilityRequestFilterNR_v1540:
                return RRC_UE_CapabilityRequestFilterNR_v1540.class;
            case UE_MRDC_CapabilityAddFRX_Mode:
                return RRC_UE_MRDC_CapabilityAddFRX_Mode.class;
            case UE_MRDC_CapabilityAddXDD_Mode:
                return RRC_UE_MRDC_CapabilityAddXDD_Mode.class;
            case UE_MRDC_CapabilityAddXDD_Mode_v1560:
                return RRC_UE_MRDC_CapabilityAddXDD_Mode_v1560.class;
            case UE_MRDC_Capability:
                return RRC_UE_MRDC_Capability.class;
            case UE_MRDC_Capability_v1560:
                return RRC_UE_MRDC_Capability_v1560.class;
            case UE_NR_CapabilityAddFRX_Mode:
                return RRC_UE_NR_CapabilityAddFRX_Mode.class;
            case UE_NR_CapabilityAddFRX_Mode_v1540:
                return RRC_UE_NR_CapabilityAddFRX_Mode_v1540.class;
            case UE_NR_CapabilityAddXDD_Mode:
                return RRC_UE_NR_CapabilityAddXDD_Mode.class;
            case UE_NR_CapabilityAddXDD_Mode_v1530:
                return RRC_UE_NR_CapabilityAddXDD_Mode_v1530.class;
            case UE_NR_Capability:
                return RRC_UE_NR_Capability.class;
            case UE_NR_Capability_v1530:
                return RRC_UE_NR_Capability_v1530.class;
            case UE_NR_Capability_v1540:
                return RRC_UE_NR_Capability_v1540.class;
            case UE_NR_Capability_v1550:
                return RRC_UE_NR_Capability_v1550.class;
            case UE_NR_Capability_v1560:
                return RRC_UE_NR_Capability_v1560.class;
            case UERadioAccessCapabilityInformation:
                return RRC_UERadioAccessCapabilityInformation.class;
            case UERadioAccessCapabilityInformation_IEs:
                return RRC_UERadioAccessCapabilityInformation_IEs.class;
            case UERadioPagingInformation:
                return RRC_UERadioPagingInformation.class;
            case UERadioPagingInformation_IEs:
                return RRC_UERadioPagingInformation_IEs.class;
            case UE_TimersAndConstants:
                return RRC_UE_TimersAndConstants.class;
            case UL_AM_RLC:
                return RRC_UL_AM_RLC.class;
            case UL_CCCH1_Message:
                return RRC_UL_CCCH1_Message.class;
            case UL_CCCH1_MessageType:
                return RRC_UL_CCCH1_MessageType.class;
            case UL_CCCH_Message:
                return RRC_UL_CCCH_Message.class;
            case UL_CCCH_MessageType:
                return RRC_UL_CCCH_MessageType.class;
            case UL_DataSplitThreshold:
                return RRC_UL_DataSplitThreshold.class;
            case UL_DCCH_Message:
                return RRC_UL_DCCH_Message.class;
            case UL_DCCH_MessageType:
                return RRC_UL_DCCH_MessageType.class;
            case ULInformationTransfer:
                return RRC_ULInformationTransfer.class;
            case ULInformationTransfer_IEs:
                return RRC_ULInformationTransfer_IEs.class;
            case ULInformationTransferMRDC:
                return RRC_ULInformationTransferMRDC.class;
            case ULInformationTransferMRDC_IEs:
                return RRC_ULInformationTransferMRDC_IEs.class;
            case UL_UM_RLC:
                return RRC_UL_UM_RLC.class;
            case UplinkConfigCommon:
                return RRC_UplinkConfigCommon.class;
            case UplinkConfigCommonSIB:
                return RRC_UplinkConfigCommonSIB.class;
            case UplinkConfig:
                return RRC_UplinkConfig.class;
            case UplinkTxDirectCurrentBWP:
                return RRC_UplinkTxDirectCurrentBWP.class;
            case UplinkTxDirectCurrentCell:
                return RRC_UplinkTxDirectCurrentCell.class;
            case UplinkTxDirectCurrentList:
                return RRC_UplinkTxDirectCurrentList.class;
            case VarMeasConfig:
                return RRC_VarMeasConfig.class;
            case VarMeasReport:
                return RRC_VarMeasReport.class;
            case VarMeasReportList:
                return RRC_VarMeasReportList.class;
            case VarPendingRNA_Update:
                return RRC_VarPendingRNA_Update.class;
            case VarResumeMAC_Input:
                return RRC_VarResumeMAC_Input.class;
            case VarShortMAC_Input:
                return RRC_VarShortMAC_Input.class;
            case VictimSystemType:
                return RRC_VictimSystemType.class;
            case ZP_CSI_RS_Resource:
                return RRC_ZP_CSI_RS_Resource.class;
            case ZP_CSI_RS_ResourceId:
                return RRC_ZP_CSI_RS_ResourceId.class;
            case ZP_CSI_RS_ResourceSet:
                return RRC_ZP_CSI_RS_ResourceSet.class;
            case ZP_CSI_RS_ResourceSetId:
                return RRC_ZP_CSI_RS_ResourceSetId.class;
            default:
                throw new IncorrectImplementationException();
        }
    }
}
