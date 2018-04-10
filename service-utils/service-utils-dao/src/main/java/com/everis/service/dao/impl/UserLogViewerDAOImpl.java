/**
 * 
 *//*
package com.everis.service.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.everis.service.dao.UserLogViewerDAO;
import com.everis.service.domain.UserLogDataTO;

*//**
 * @author gregorio.de.chaves
 *//*
@Repository
public class UserLogViewerDAOImpl implements UserLogViewerDAO {

	private Logger log = LoggerFactory.getLogger(UserLogViewerDAOImpl.class);

//	private EntityManagerFactory factory;

	//private EntityManager entityManager;

	public UserLogViewerDAOImpl() {

		//this.factory = Persistence.createEntityManagerFactory("UserLogViewer");
	//	this.entityManager = factory.createEntityManager();
	}

	@Override
	public List<UserLogDataTO> getUserInfoByFilter(String filter, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLogDataTO> getUserInfoByRangeDate(Date date1, Date date2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLogDataTO> getAllUserData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserLogDataTO> getUserInfoByFilter(String filter, String keyword) throws Exception {

		log.debug("[DAO] - Obtendo informacoes via filtro: " + filter + " palavra chave: " + keyword);

		List<UserLogDataTO> listUserTO = new ArrayList<UserLogDataTO>();
		
		if (keyword.indexOf("-") != -1){
			keyword = keyword.replace("-", "/");
		}

		UserLogDataEntity userLogData = new UserLogDataEntity();

		try {

			this.entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Query query = null;

			if (filter.equalsIgnoreCase(UserLogViewerDAOConstants.CPF_CNPJ_FILTER)) {
				query = entityManager.createQuery(UserLogViewerDAOQueryConstants.QUERY_FILTER_CPFCNPJ);
			} else if (filter.equalsIgnoreCase(UserLogViewerDAOConstants.DATA_HORA_FILTER)) {
				query = entityManager.createNativeQuery(UserLogViewerDAOQueryConstants.QUERY_FILTER_DATA_HORA);
			} else if (filter.equalsIgnoreCase(UserLogViewerDAOConstants.CLASS_FILTER)) {
				query = entityManager.createNativeQuery(UserLogViewerDAOQueryConstants.QUERY_FILTER_CLASS_ERRO);
			} else if (filter.equalsIgnoreCase(UserLogViewerDAOConstants.ORIGEM_FILTER)) {
				query = entityManager.createNativeQuery(UserLogViewerDAOQueryConstants.QUERY_FILTER_ORIGEM);
			} else if (filter.equalsIgnoreCase(UserLogViewerDAOConstants.STATUS_FILTER)) {
				query = entityManager.createNativeQuery(UserLogViewerDAOQueryConstants.QUERY_FILTER_STATUS);
			} else if (filter.equalsIgnoreCase(UserLogViewerDAOConstants.ID_STRING)) {
				query = entityManager.createNativeQuery(UserLogViewerDAOQueryConstants.QUERY_FILTER_ID);
			}

			if (filter.equalsIgnoreCase(UserLogViewerDAOConstants.DATA_HORA_FILTER)
					|| filter.equalsIgnoreCase(UserLogViewerDAOConstants.CLASS_FILTER)
					|| filter.equalsIgnoreCase(UserLogViewerDAOConstants.ORIGEM_FILTER)) {
				query.setParameter(UserLogViewerDAOConstants.KEYWORD_STRING, "%" + keyword + "%");
			} else {
				query.setParameter(UserLogViewerDAOConstants.KEYWORD_STRING, keyword);
			}

			List<UserLogDataEntity> listReturned = query.getResultList();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			for (UserLogDataEntity userLogDataEntity : listReturned) {
				
				UserLogDataTO userLogDataTO = new UserLogDataTO();
				userLogDataTO.setId(userLogDataEntity.getId());
				userLogDataTO.setCpfcnpj(userLogDataEntity.getCpfcnpj());

				Timestamp stamp = (Timestamp) userLogDataEntity.getDataHora();

				userLogDataTO.setDataHora(dateFormat.format(stamp.getTime()));

				userLogDataTO.setClassificacaoErro(convertTipo(userLogDataEntity.getClassificacaoErro()));

				userLogDataTO.setXmlEntrada(convertClobToString(userLogDataEntity.getXmlEntrada()));
				userLogDataTO.setXmlSaida(convertClobToString(userLogDataEntity.getXmlSaida()));

				userLogDataTO.setOrigem(convertOrgn(userLogDataEntity.getOrigem()));
				userLogDataTO.setStatus(convertStats(userLogDataEntity.getStatus()));

				listUserTO.add(userLogDataTO);
			}

		} catch (Exception e) {
			log.debug("[DAO] - Erro ao efetuar a consulta. ", e);
			throw e;
		} finally {
			entityManager.close();
		}

		log.debug("[DAO] - Consulta efetuada com sucesso.");

		return listUserTO;

	}

	public List<UserLogDataTO> getAllUserData() throws Exception {

		log.debug("[DAO] - Obtendo todos os dados.");

		List<UserLogDataTO> userLogDataTO = new ArrayList<UserLogDataTO>();

		try {

			this.entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Query query = entityManager.createNativeQuery(UserLogViewerDAOQueryConstants.QUERY_ALL_DATA);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");

			List<?> listReturned = query.getResultList();

			for (Object item : listReturned) {
				Object[] element = (Object[]) item;

				UserLogDataTO dataTO = new UserLogDataTO();

				// dataTO.setId((BigDecimal)element[0]);
				dataTO.setCpfcnpj((String) element[1]);

				Timestamp stamp = (Timestamp) element[2];
				dataTO.setDataHora(dateFormat.format(stamp.getTime()));
				dataTO.setClassificacaoErro((String) element[3]);

				dataTO.setXmlEntrada(convertClobToString((Clob) element[4]));
				dataTO.setXmlSaida(convertClobToString((Clob) element[5]));

				dataTO.setOrigem((String) element[6]);
				dataTO.setStatus((String) element[7]);
				userLogDataTO.add(dataTO);
			}

		} catch (Exception e) {
			log.debug("[DAO] - Erro ao obter todos os dados. ", e);
			throw e;
		} finally {
			entityManager.close();
		}

		log.debug("[DAO] - Consulta efetuada com sucesso.");

		return userLogDataTO;

	}

	public List<UserLogDataTO> getUserInfoByRangeDate(Date date1, Date date2) throws Exception {

		log.debug("[DAO] - Obtendo informacoes via range de data. Data1: " + date1 + " data2: " + date2);

		List<UserLogDataTO> listUserTO = new ArrayList<UserLogDataTO>();

		try {

			this.entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			Query query = null;

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			query = entityManager.createQuery(UserLogViewerDAOQueryConstants.QUERY_RANGE_DATE);
			query.setParameter(UserLogViewerDAOConstants.DATE1_STRING, date1, TemporalType.TIMESTAMP);
			query.setParameter(UserLogViewerDAOConstants.DATE2_STRING, date2, TemporalType.TIMESTAMP);

			List<UserLogDataEntity> listReturned = query.getResultList();

			for (UserLogDataEntity userLogDataEntity : listReturned) {
				
				UserLogDataTO userLogDataTO = new UserLogDataTO();
				userLogDataTO.setId(userLogDataEntity.getId());
				userLogDataTO.setCpfcnpj(userLogDataEntity.getCpfcnpj());

				Timestamp stamp = (Timestamp) userLogDataEntity.getDataHora();

				userLogDataTO.setDataHora(dateFormat.format(stamp.getTime()));

				userLogDataTO.setClassificacaoErro(convertTipo(userLogDataEntity.getClassificacaoErro()));

				userLogDataTO.setXmlEntrada(convertClobToString(userLogDataEntity.getXmlEntrada()));
				userLogDataTO.setXmlSaida(convertClobToString(userLogDataEntity.getXmlSaida()));

				userLogDataTO.setOrigem(convertOrgn(userLogDataEntity.getOrigem()));
				userLogDataTO.setStatus(convertStats(userLogDataEntity.getStatus()));

				listUserTO.add(userLogDataTO);
			}

		} catch (Exception e) {
			log.debug("[DAO] - Erro ao efetuar a consulta. ", e);
			throw e;
		} finally {
			entityManager.close();
		}

		log.debug("[DAO] - Consulta efetuada com sucesso.");

		return listUserTO;

	}

	*//**
	 * Metodo responsavel por converter o objeto clob do banco para String
	 * 
	 * @param clob
	 * @return String
	 * @throws SQLException
	 *//*
	private String convertClobToString(Clob clob) throws SQLException {

		long len = clob.length();
		String data = clob.getSubString(1, (int) len);

		return data;
	}

	private String convertTipo(int t) {

		String result = null;

		if (t == 1) {
			result = "Login - não é cliente";
		} else if (t == 2) {
			result = "Login - não possui produtos";
		} else if (t == 3) {
			result = "Cadastro - não possui produtos";
		} else if (t == 4) {
			result = "Cadastro - usuário não encontrado";
		}

		return result;

	}

	private String convertOrgn(int t) {

		String result = null;

		if (t == 1) {
			result = "Portal de Cliente";
		} else if (t == 2) {
			result = "Portal Itaú";
		} else if (t == 3) {
			result = "Aplicativo Cartões";
		} else if (t == 4) {
			result = "Aplicativo Automóvel";
		}

		return result;

	}

	private String convertStats(int t) {

		String result = null;

		if (t == 1) {
			result = "OK";
		} else if (t == 2) {
			result = "NOK";
		}
		return result;

	}

}*/