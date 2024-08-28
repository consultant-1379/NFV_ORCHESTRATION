#!/bin/sh -f
# type        sgsnl
# hw          virtual
# ss7type     itu
# ni          w5p2vmme5

gsh modify_apn_redirection -arfma reject -arfsa subscribed

gsh modify_apn_resolution_cc -ccn CC -ccti no -cctv 1

gsh modify_apn_resolution_infix_order -ai1 si -ai2 cc -ai3 ga

gsh modify_apn_resolution_msisdn -msd 3 -lsd 9

gsh modify_closed_subscriber_group -po on -srt 3000

gsh create_diameter_application -da s6a -ls true -dh w5p2vmme5 -rn ericsson.se

gsh create_diameter_peer -ip 10.42.83.241 -pn 1 -portno 3868 -sip NULL -prio NULL -dh w5p2vmme5 -rn ericsson.se

gsh create_diameter_peer -ip 172.22.49.1 -pn 2 -portno 3868 -sip NULL -prio NULL -dh w5p2vmme5 -rn ericsson.se

gsh create_dns -dn epc.mnc099.mcc240.3gppnetwork.org. -qt iterative -ct 604800 -lct 300 -dcm central

gsh create_dns_server -dn epc.mnc099.mcc240.3gppnetwork.org. -ns w5p2dns1.

gsh create_dns_server_address -dn epc.mnc099.mcc240.3gppnetwork.org. -ns w5p2dns1. -ip 10.70.250.132

gsh modify_eea_algorithm -name eea0 -prio 3

gsh modify_eea_algorithm -name eea1 -prio 2

gsh modify_eea_algorithm -name eea2 -prio 1

gsh modify_eea_algorithm -name eea3 -prio 0

gsh modify_eia_algorithm -name eia1 -prio 2

gsh modify_eia_algorithm -name eia2 -prio 1

gsh modify_eia_algorithm -name eia3 -prio 0

gsh create_eth_port -eqp 1.1 -ep 1

gsh create_eth_port -eqp 1.2 -ep 1

gsh create_eth_vlan -eqp 1.1 -ep 1 -vid 0

gsh create_eth_vlan -eqp 1.2 -ep 1 -vid 0

gsh create_event_job -epi EBM -eji EbmLog -des Pre-defined\ EBM\ log\ job.\ Cannot\ be\ deleted. -ef NULL -rjs STOPPED -foe true -sdia NULL -sdp NULL -rp FIFTEEN_MIN -soe false -fct NULL -sct NULL -etr NULL -egr "(-epi EBM -egi MmeEbmGroup)"

gsh create_event_job -epi EBM -eji EbmStream -des Pre-defined\ EBM\ stream\ job.\ Cannot\ be\ deleted. -ef NULL -rjs STOPPED -foe false -sdia NULL -sdp NULL -rp NULL -soe true -fct NULL -sct NULL -etr NULL -egr "(-epi EBM -egi MmeEbmGroup)"

gsh modify_feature -name 3gdt -state off

gsh modify_feature -name aace_support -state off

gsh modify_feature -name access_restriction -state off

gsh modify_feature -name adc -state off

gsh modify_feature -name add -state off

gsh modify_feature -name anr_support -state off

gsh modify_feature -name apn_local_breakout_control -state on

gsh modify_feature -name apn_oi_replacement -state off

gsh modify_feature -name apn_redirection -state off

gsh modify_feature -name apn_resolution_extension -state off

gsh modify_feature -name authentication_stationary_subscriber -state off

gsh modify_feature -name blacklisting_ggsn -state off

gsh modify_feature -name camel_phase3 -state off

gsh modify_feature -name cell_change_reporting -state off

gsh modify_feature -name combined_procedures_gs_if -state off

gsh modify_feature -name conversational_qos_class -state off

gsh modify_feature -name csfb_to_1xrtt -state off

gsh modify_feature -name csfb_to_wg -state off

gsh modify_feature -name data_compression_v42bis -state off

gsh modify_feature -name dedicated_bearers -state on

gsh modify_feature -name detach_inactive_subscriber -state off

gsh modify_feature -name detach_inactive_subscriber_da -state off

gsh modify_feature -name detach_inactive_subscriber_wcdma -state off

gsh modify_feature -name dual_access_lw -state on

gsh modify_feature -name dual_access_support -state off

gsh modify_feature -name dual_transfer_mode -state off

gsh modify_feature -name ebm -state on

gsh modify_feature -name edge_support -state off

gsh modify_feature -name embms_support -state off

gsh modify_feature -name equivalent_plmns -state on

gsh modify_feature -name gb_over_ip -state off

gsh modify_feature -name geo_redundant_pool -state off

gsh modify_feature -name gsm_adaptive_paging -state off

gsh modify_feature -name gtp_user_location -state off

gsh modify_feature -name gtpprime -state off

gsh modify_feature -name gw_blacklisting -state off

gsh modify_feature -name gw_failure_restoration_gsm -state off

gsh modify_feature -name gw_failure_restoration_lte -state off

gsh modify_feature -name gw_failure_restoration_wcdma -state off

gsh modify_feature -name highest_qos_imsi -state off

gsh modify_feature -name highest_qos_rnc -state off

gsh modify_feature -name imei_check -state off

gsh modify_feature -name integrated_traffic_capture -state on

gsh modify_feature -name ipsec_support -state off

gsh modify_feature -name lawful_interception -state off

gsh modify_feature -name loadbased_ho -state off

gsh modify_feature -name location_services -state off

gsh modify_feature -name loc_based_ip_allocation_gsm -state off

gsh modify_feature -name loc_based_ip_allocation_lte -state off

gsh modify_feature -name loc_based_ip_allocation_wcdma -state off

gsh modify_feature -name lte_adaptive_paging -state off

gsh modify_feature -name mbms_broadcast -state off

gsh modify_feature -name misconfig_mt_id -state off

gsh modify_feature -name mme_pool -state off

gsh modify_feature -name mmtel -state off

gsh modify_feature -name mobility_based_policy_lte -state off

gsh modify_feature -name mobility_based_policy_wcdma -state off

gsh modify_feature -name mocn_gsm -state off

gsh modify_feature -name mocn_lte -state off

gsh modify_feature -name mocn_wcdma -state off

gsh modify_feature -name msc_pool_gs -state off

gsh modify_feature -name msc_pool_sgs -state off

gsh modify_feature -name msc_pool_sv -state off

gsh modify_feature -name multiple_plmn_support -state on

gsh modify_feature -name national_roaming_restriction -state off

gsh modify_feature -name non_3gpp_compliant_ue -state off

gsh modify_feature -name nw_init_sec_pdpctxt -state off

gsh modify_feature -name pci_compression_rfc1144 -state off

gsh modify_feature -name pfc_flow_control -state off

gsh modify_feature -name prioritise_payload_users -state off

gsh modify_feature -name ps_ho -state on

gsh modify_feature -name public_warning_system -state off

gsh modify_feature -name ran_cause_codes_in_scdr -state off

gsh modify_feature -name rim_transfer -state off

gsh modify_feature -name roaming_restriction_based_on_imei_tac -state off

gsh modify_feature -name s3_s4_architecture_support -state off

gsh modify_feature -name s_cdr_cause_code_ext -state off

gsh modify_feature -name secondary_context -state on

gsh modify_feature -name selective_service_request -state off

gsh modify_feature -name sgsn_pool -state off

gsh modify_feature -name sms_over_sgs -state off

gsh modify_feature -name srns_relocation -state off

gsh modify_feature -name srvcc -state off

gsh modify_feature -name ss7_over_ip -state off

gsh modify_feature -name streaming_qos_class -state on

gsh modify_feature -name subscription_restriction -state off

gsh modify_feature -name support_henb -state off

gsh modify_feature -name triple_access_lwg -state off

gsh modify_feature -name ue_signalling_control -state on

gsh modify_feature -name ue_trace_mme -state on

gsh modify_feature -name closed_subscriber_group -state off

gsh modify_feature -name regional_subscription -state off

gsh modify_feature -name wifi_ue_throughput_mobility -state off

gsh modify_geo_redundant_pool -ubdo wholePool -spi NULL -pbi 180

gsh create_gn -id 1 -gst neverTryPgw -sfr off -udmr false -u4dmr false -unidq false -urrds false -ee all

gsh create_imsins -imsi 24099 -rs home -dn mnc099.mcc240.gprs -np e214 -ac true -na international -rd 15 -ad 491729009999 -m1 NULL -m2 NULL -m3 NULL -eplp NULL -rn ericsson.se -hn hss -s8 gtp -gcc NULL -wcc NULL -lcc NULL -rfsp NULL -arph 1 -arpm 2 -gss gr -vlbo NULL -aifv true -mavs 1 -mavr 2 -earplr 0 -st true -sra noaction -ssc NULL

gsh create_imsins -imsi 24011 -rs home -dn mnc099.mcc240.gprs -np e214 -ac true -na international -rd 15 -ad 491729009999 -m1 NULL -m2 NULL -m3 NULL -eplp NULL -rn ericsson.se -hn hss -s8 gtp -gcc NULL -wcc NULL -lcc NULL -rfsp NULL -arph 1 -arpm 2 -gss gr -vlbo NULL -aifv true -mavs 1 -mavr 2 -earplr 0 -st true -sra noaction -ssc NULL

gsh create_imsins -imsi 26287 -rs home -dn mnc087.mcc262.gprs -np e214 -ac true -na national -rd 15 -ad 491729009999 -m1 NULL -m2 NULL -m3 NULL -eplp NULL -rn dallas.se -hn NULL -s8 gtp -gcc NULL -wcc NULL -lcc NULL -rfsp NULL -arph 1 -arpm 2 -gss gr -vlbo NULL -aifv true -mavs 1 -mavr 2 -earplr 0 -st true -sra noaction -ssc NULL

gsh create_inbound_pf_policy -ifp ETH_1_1_0_0_VPN1

gsh create_inbound_pf_policy -ifp ETH_1_2_0_0_VPN1

gsh create_inbound_pf_rule -ifp ETH_1_1_0_0_VPN1 -fr 1 -r permit -p ip -d NULL -dm NULL -sip 0.0.0.0 -sipm 0.0.0.0 -dip 0.0.0.0 -dipm 0.0.0.0 -pp 0 -ppq gt -ipo true -sp 0 -spq gt -dp 0 -dpq gt -tf NULL -tfm NULL -it 0 -itq gt -sd NULL -lt false

gsh create_inbound_pf_rule -ifp ETH_1_2_0_0_VPN1 -fr 1 -r permit -p ip -d NULL -dm NULL -sip 0.0.0.0 -sipm 0.0.0.0 -dip 0.0.0.0 -dipm 0.0.0.0 -pp 0 -ppq gt -ipo true -sp 0 -spq gt -dp 0 -dpq gt -tf NULL -tfm NULL -it 0 -itq gt -sd NULL -lt false

gsh create_ip_interface -ifn ETH_1_1_0_0_VPN1 -ip 10.70.244.3 -mask 255.255.255.248 -rip NULL -eqp 1.1 -ep 1 -vid 0 -ifp ETH_1_1_0_0_VPN1 -nw VPN1

gsh create_ip_interface -ifn ETH_1_2_0_0_VPN1 -ip 10.70.244.4 -mask 255.255.255.248 -rip NULL -eqp 1.2 -ep 1 -vid 0 -ifp ETH_1_2_0_0_VPN1 -nw VPN1

gsh create_ip_network -nw VPN1

gsh create_ip_service -sn DNS -dscp NULL -nw VPN1

gsh create_ip_service_address -sn DNS -ip 10.70.244.33

gsh create_ip_service -sn NTP -dscp NULL -nw VPN1

gsh create_ip_service_address -sn NTP -ip 10.70.244.33

gsh create_ip_service -sn OAM -dscp NULL -nw VPN1

gsh create_ip_service_address -sn OAM -ip 10.70.244.33

gsh create_ip_service -sn S1-MME-1 -dscp NULL -nw VPN1

gsh create_ip_service_address -sn S1-MME-1 -ip 10.70.244.9

gsh create_ip_service -sn S1-MME-2 -dscp NULL -nw VPN1

gsh create_ip_service_address -sn S1-MME-2 -ip 10.70.244.10

gsh create_ip_service -sn S10-GTP-C -dscp NULL -nw VPN1

gsh create_ip_service_address -sn S10-GTP-C -ip 10.70.244.25

gsh create_ip_service -sn S11-GTP-C -dscp NULL -nw VPN1

gsh create_ip_service_address -sn S11-GTP-C -ip 10.70.244.25

gsh create_ip_service -sn S3-GTP-C -dscp NULL -nw VPN1

gsh create_ip_service_address -sn S3-GTP-C -ip 10.70.244.25

gsh create_ip_service -sn S6a-1 -dscp NULL -nw VPN1

gsh create_ip_service_address -sn S6a-1 -ip 10.70.244.17

gsh create_ip_service -sn S6a-2 -dscp NULL -nw VPN1

gsh create_ip_service_address -sn S6a-2 -ip 10.70.244.18

gsh create_local_diameter_host -dh w5p2vmme5 -rn ericsson.se -no 2

gsh modify_mme -apac true -apar false -atrl 1000 -ate false -grtl 240 -trc 10 -asmp true -tgbd false -mbu 8 -pumn true -pudn defaultbearer -atrla 0 -atrlg 0

gsh modify_ms_move -mms gtwt -lms 3GPP

gsh modify_ne -cnid NULL -isdn 00 -ni w5p2vmme5 -nl 4 -rbnri 6 -mgi 32769 -mc 5 -rmc 64 -daf 0 -tlo currentTai -ssm allUe -ivh false -dan NULL -hcmv 2 -cpsm false -dpmm false -qna false -aifav true -gtdm false -lrc 14 -hhfdut 60

gsh modify_node_function -name cc90_cc91_indicate_forbidden_ta -state off

gsh modify_node_function -name cell_trace_ueid_mapping -state off

gsh modify_node_function -name diameter_nas_roaming_not_allowed_cc_remap -state off

gsh modify_node_function -name diameter_nas_user_unknown_cc_remap -state off

gsh modify_node_function -name ebm_log_hide_msisdn -state on

gsh modify_node_function -name nas_token_validation_enabled -state on

gsh modify_node_function -name odb_allowed_apn_list -state off

gsh modify_node_function -name odb_allowed_apn_list_for_roaming_subscriber -state off

gsh modify_node_function -name session_mobility_log -state on

gsh modify_node_function -name sgw_selection_based_on_all_apn_lbo -state off

gsh modify_node_function -name sms -state off

gsh modify_node_function -name static_gw_selection -state off

gsh modify_node_function -name static_gw_selection_typea_dns_fallback -state off

gsh modify_node_function -name static_mme_sgsn_selection -state off

gsh modify_node_function -name static_mme_sgsn_selection_snaptr_dns_fallback -state off

gsh modify_node_function -name stream_cell_trace_ueid_mapping -state off

gsh modify_node_function -name allow_csfb_during_voice_call -state on

gsh modify_node_function -name diameter_nas_severe_nw_failure_cc_remap_home -state off

gsh modify_node_function -name diameter_nas_severe_nw_failure_cc_remap_roamers -state off

gsh modify_node_function -name sbc_sending_criticality_diagnostic -state on

gsh modify_node_function -name pm_job_auto_update -state off

gsh modify_node_function -name random_emergency_pgw_selection -state off

gsh modify_node_function -name s1_include_spid_in_dl_nas_transport -state off

gsh create_paging_profile -id 1 -enb 0 -ta 0 -talist 4 -ptpt NULL -enbl 0

gsh create_paging_profile -id 2 -enb 0 -ta 2 -talist 3 -ptpt NULL -enbl 0

gsh create_paging_profile -id 3 -enb 2 -ta 2 -talist 2 -ptpt NULL -enbl 0

gsh create_paging_selection -prio 100 -tn NULL -tx NULL -arpn NULL -arpx NULL -qcin NULL -qcix NULL -apn NULL -imsi NULL -imei NULL -c When\ no\ other\ rule\ match,\ the\ default\ paging_profile\ is\ used -id 1

gsh create_plmn -mcc 240 -mnc 99 -pn 24099 -fnn vepc_24099 -snn vepc_24099 -ci false -me false -s5 gtp -local true -voip no -sl true

gsh create_plmn -mcc 262 -mnc 87 -pn 26287 -fnn NULL -snn NULL -ci false -me false -s5 gtp -local true -voip no -sl true

gsh modify_qos -gbg NULL -gbw NULL -pe true -co EF -st AF2.1 -in1 AF1.1 -in2 BE -in3 BE -bg BE -gosq off -smah on -smam off -smas off -cqm standard -idgv 0

gsh create_router_instance -eqp 0.0 -nw VPN1 -ip NULL -sn NULL -sc NULL -sl NULL

gsh modify_s102 -sa 2 -tack 500 -mnid imsi -era off

gsh create_s11 -s11 s11 -x2ho 1000

gsh create_s1_mme -s1 s1_mme -uecm 1000 -info full -uecr 4000 -uecrwg 2000 -erabm 1500 -cdrsw 7 -cdrpd None -cdbpd 36 -warn 2000 -kill 2000 -hrl 100 -thog 1500 -nhog 2 -rp 30 -rps 0 -tpt 3 -idt 60 -mrt 58 -saf 10 -trc 1000 -tsr 1000 -thn 5000 -thra 2000 -tics 1500 -ucr 1000 -test 300 -wad 300 -s1rh 3000 -tgbd 2000 -menb 10 -sar true -esai false -plrmtlr false -vbhc release_list -dpdp false -no 1

gsh modify_s6a -ar 1 -rar 100000 -aanh false

gsh create_sctp_end_point -no 1 -portno 36412 -pn S1_MME_SCTP -secsn S1-MME-2 -primsn S1-MME-1

gsh create_sctp_end_point -no 2 -portno 36412 -pn S6a_SCTP -secsn S6a-2 -primsn S6a-1

gsh create_sctp_profile -pn S1_MME_SCTP -minrto 80 -maxrto 400 -irto 200 -ai 3 -bi 2 -cl 60000 -inccl 0 -keycp 4000 -assrtx 8 -pathrtx 2 -initrt 8 -shrt 5 -hb 30000 -usehb 1 -mis 17 -mos 17 -pmtu 1500 -userbufsize 32768 -thruserbufsize 24576 -iarwnd 32768 -maxootb 100 -tootb 3600000 -burst 4 -nperc 85 -sackt 40 -bundling 1 -bunt 10 -sackfreq 2 -authr 50 -hbr 50 -hbmb 1 -ihb 5000 -smf 50 -ppa 0 -minat 1 -maxat 10 -atf 0 -vm 0 -ppmr 0 -pmtuv6 1480 -hbwps 1 -tzrs 1 -dscp 40 -mfr 1 -sscwndf 200 -cwndmin 0 -tcs 0

gsh create_sctp_profile -pn S6a_SCTP -minrto 80 -maxrto 400 -irto 200 -ai 3 -bi 2 -cl 60000 -inccl 0 -keycp 4000 -assrtx 8 -pathrtx 2 -initrt 8 -shrt 5 -hb 30000 -usehb 1 -mis 17 -mos 17 -pmtu 1500 -userbufsize 32768 -thruserbufsize 24576 -iarwnd 32768 -maxootb 100 -tootb 3600000 -burst 4 -nperc 85 -sackt 40 -bundling 1 -bunt 10 -sackfreq 2 -authr 50 -hbr 50 -hbmb 1 -ihb 5000 -smf 50 -ppa 0 -minat 1 -maxat 10 -atf 0 -vm 0 -ppmr 0 -pmtuv6 1480 -hbwps 1 -tzrs 1 -dscp 40 -mfr 1 -sscwndf 200 -cwndmin 0 -tcs 0

gsh create_sctp_profile -pn SctpProfile_0 -minrto 80 -maxrto 400 -irto 200 -ai 3 -bi 2 -cl 60000 -inccl 0 -keycp 4000 -assrtx 8 -pathrtx 2 -initrt 8 -shrt 5 -hb 30000 -usehb 1 -mis 17 -mos 17 -pmtu 1500 -userbufsize 32768 -thruserbufsize 24576 -iarwnd 32768 -maxootb 100 -tootb 3600000 -burst 4 -nperc 85 -sackt 40 -bundling 1 -bunt 10 -sackfreq 2 -authr 50 -hbr 50 -hbmb 1 -ihb 5000 -smf 50 -ppa 0 -minat 1 -maxat 10 -atf 0 -vm 0 -ppmr 0 -pmtuv6 1480 -hbwps 1 -tzrs 1 -dscp 40 -mfr 1 -sscwndf 200 -cwndmin 0 -tcs 0

gsh modify_sctp_sys -noass 8192 -icmpact 1 -tmesbufs 3 -sctpoutbufs 1500 -socrecbufs 0 -twfc 20 -twfnb 1 -trec 5000 -ulmbs 500000 -totasmem 0 -ulmsfipart 70 -wbct 70 -upii 0 -udii 0 -tmir 0

gsh modify_sgs_ap -ts12a1 62 -tamr false -ts10rid 4 -ts61rlu 10 -ts8red 4 -ts9red 4

gsh modify_sgsn -arsp true -aqu upgradeAllowed -arfma reject -arfsa subscribed -artsa false -lpcpu 0 -mitl 300 -mfcr false -rea false -tire false -t14 5500 -mscg false -mscw false -mfcg false -nhoa false -numn true

gsh modify_sls -ts 6 -tl 30

gsh modify_ss7_sys -chobs 2000 -reroutbms 2000 -tbind 20 -tbexp 150 -tinitconf 100 -tmsg 10 -treinit 100 -timerm3burst 10 -lrbs 1 -tlretr 5 -icl1 700 -icl2 800 -icl3 900 -atl1 650 -atl2 750 -atl3 850 -swbct 70 -trdss 10 -udbs 10 -tca 60000 -noss7 1 -mhwbbs 1 -mnbwp 100 -tbj 50 -tds 5 -bbs 0 -eb 5200 -pu yes -tsccpupd 1000

gsh create_static_route -eqp 0.0 -nw VPN1 -dip 0.0.0.0 -mask 0.0.0.0 -gip 10.70.244.2

gsh modify_subscriber_authentication -mavp on -savp on

gsh modify_tai_list_option -um visited -mtll 1 -mta 720

gsh modify_ue_remedy -hio false

gsh modify_ue_trace_log -rp 15

gsh create_ntp_server -ip 10.44.216.171

gsh create_ntp_server -ip 10.44.216.172

gsh create_ntp_server -ip 10.44.216.173

gsh create_snmp_target_v2c -ip 10.59.159.200 -name OSS -port 162 -community public

