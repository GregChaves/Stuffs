package com.everis.service.dao.util;

public class UserLogViewerDAOQueryConstants {

	public static final String QUERY_FILTER_CPFCNPJ = "SELECT usrlog FROM UserLogDataEntity usrlog WHERE usrlog.cpfcnpj = :keyword";
	
	public static final String QUERY_FILTER_DATA_HORA = "SELECT usrlog FROM UserLogDataEntity usrlog WHERE usrlog.dataHora LIKE :keyword";

	public static final String QUERY_FILTER_CLASS_ERRO = "SELECT usrlog FROM UserLogDataEntity usrlog WHERE usrlog.classificacaoErro LIKE :keyword";
	
	public static final String QUERY_FILTER_ORIGEM = "SELECT usrlog FROM UserLogDataEntity usrlog WHERE usrlog.origem LIKE :keyword";
	
	public static final String QUERY_FILTER_STATUS = "Select * from transacional.wclmclinaoacslog where CLINAOACSLOGSITCOD = :keyword";
	
	public static final String QUERY_FILTER_ID = "SELECT usrlog FROM UserLogDataEntity usrlog WHERE usrlog.id = :keyword";
	
	public static final String QUERY_ALL_DATA = "SELECT usrlog FROM UserLogDataEntity";

	public static final String QUERY_RANGE_DATE = "SELECT usrlog FROM UserLogDataEntity usrlog WHERE usrlog.dataHora between :date1 and :date2";
	
}
