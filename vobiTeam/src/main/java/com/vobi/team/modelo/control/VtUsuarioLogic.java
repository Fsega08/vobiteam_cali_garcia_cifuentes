package com.vobi.team.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.team.dataaccess.dao.IVtProyectoUsuarioDAO;
import com.vobi.team.dataaccess.dao.IVtUsuarioArtefactoDAO;
import com.vobi.team.dataaccess.dao.IVtUsuarioDAO;
import com.vobi.team.dataaccess.dao.IVtUsuarioRolDAO;
import com.vobi.team.exceptions.ZMessManager;
import com.vobi.team.modelo.VtProyecto;
import com.vobi.team.modelo.VtProyectoUsuario;
import com.vobi.team.modelo.VtRol;
import com.vobi.team.modelo.VtUsuario;
import com.vobi.team.modelo.VtUsuarioArtefacto;
import com.vobi.team.modelo.VtUsuarioRol;
import com.vobi.team.modelo.dto.VtUsuarioDTO;
import com.vobi.team.service.mail.IMailService;
import com.vobi.team.utilities.Utilities;


/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 *
 */
@Scope("singleton")
@Service("VtUsuarioLogic")
public class VtUsuarioLogic implements IVtUsuarioLogic {
	private static final Logger log = LoggerFactory.getLogger(VtUsuarioLogic.class);

	/**
	 * DAO injected by Spring that manages VtUsuario entities
	 *
	 */
	@Autowired
	private IVtUsuarioDAO vtUsuarioDAO;

	/**
	 * DAO injected by Spring that manages VtProyectoUsuario entities
	 *
	 */
	@Autowired
	private IVtProyectoUsuarioDAO vtProyectoUsuarioDAO;
	
	
	/**
	 * DAO injected by Spring that manages VtUsuarioArtefacto entities
	 *
	 */
	@Autowired
	private IVtUsuarioArtefactoDAO vtUsuarioArtefactoDAO;

	/**
	 * DAO injected by Spring that manages VtUsuarioRol entities
	 *
	 */
	@Autowired
	private IVtUsuarioRolDAO vtUsuarioRolDAO;

	/**
	 * Logic injected by Spring that manages VtEmpresa entities
	 *
	 */
	@Autowired
	private IVtEmpresaLogic logicVtEmpresa1;

	@Autowired
	private IVtProyectoUsuarioLogic proyectoUsuarioLogic;

	@Autowired
	private IVtRolLogic vtRolLogic;

	@Autowired
	private IVtUsuarioRolLogic vtUsuarioRolLogic;
	
	@Autowired
	private IMailService mailService;

	@Transactional(readOnly = true)
	public List<VtUsuario> getVtUsuario() throws Exception {
		log.debug("finding all VtUsuario instances");

		List<VtUsuario> list = new ArrayList<VtUsuario>();

		try {
			list = vtUsuarioDAO.findAll();
		} catch (Exception e) {
			log.error("finding all VtUsuario failed", e);
			throw new ZMessManager().new GettingException(ZMessManager.ALL +
					"VtUsuario");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveVtUsuario(VtUsuario entity) throws Exception {
		log.debug("saving VtUsuario instance");

		try {
			if (entity.getVtEmpresa() == null) {
				throw new ZMessManager().new ForeignException("vtEmpresa");
			}

			if (entity.getActivo() == null) {
				throw new ZMessManager().new EmptyFieldException("activo");
			}

			if ((entity.getActivo() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("activo");
			}

			if (entity.getClave() == null) {
				throw new ZMessManager().new EmptyFieldException("clave");
			}

			if ((entity.getClave() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getClave(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("clave");
			}

			if (entity.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"fechaCreacion");
			}

			if (entity.getLogin() == null) {
				throw new ZMessManager().new EmptyFieldException("login");
			}

			if ((entity.getLogin() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getLogin(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("login");
			}

			if (entity.getNombre() == null) {
				throw new ZMessManager().new EmptyFieldException("nombre");
			}

			if ((entity.getNombre() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getNombre(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombre");
			}

			if (entity.getUsuCreador() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCreador");
			}

			VtUsuario vtUsuario = findUsuarioByLogin(entity.getLogin());
			if(vtUsuario != null){
				if (entity.getLogin().equals(vtUsuario.getLogin()) == true) {
					throw new Exception("Ya hay un usuario con ese login.");
				}
			}

			boolean mailCorrecto = Utilities.validateEmail(entity.getLogin());
			if(!mailCorrecto){
				throw new Exception("El correo no es valido");
			}

			//            if (entity.getUsuaCodigo() == null) {
			//                throw new ZMessManager().new EmptyFieldException("usuaCodigo");
			//            }

			if (entity.getVtEmpresa().getEmprCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"emprCodigo_VtEmpresa");
			}

			//            if (getVtUsuario(entity.getUsuaCodigo()) != null) {
			//                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
			//            }

			vtUsuarioDAO.save(entity);

			log.debug("save Usuario successful");
		} catch (Exception e) {
			log.error("save VtUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteVtUsuario(VtUsuario entity) throws Exception {
		log.debug("deleting VtUsuario instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("VtUsuario");
		}

		if (entity.getUsuaCodigo() == null) {
			throw new ZMessManager().new EmptyFieldException("usuaCodigo");
		}

		List<VtProyectoUsuario> vtProyectoUsuarios = null;
		List<VtUsuarioArtefacto> vtUsuarioArtefactos = null;
		List<VtUsuarioRol> vtUsuarioRols = null;

		try {
			vtProyectoUsuarios = vtProyectoUsuarioDAO.findByProperty("vtUsuario.usuaCodigo",
					entity.getUsuaCodigo());

			if (Utilities.validationsList(vtProyectoUsuarios) == true) {
				throw new ZMessManager().new DeletingException(
						"vtProyectoUsuarios");
			}

			vtUsuarioArtefactos = vtUsuarioArtefactoDAO.findByProperty("vtUsuario.usuaCodigo",
					entity.getUsuaCodigo());

			if (Utilities.validationsList(vtUsuarioArtefactos) == true) {
				throw new ZMessManager().new DeletingException(
						"vtUsuarioArtefactos");
			}

			vtUsuarioRols = vtUsuarioRolDAO.findByProperty("vtUsuario.usuaCodigo",
					entity.getUsuaCodigo());
			if (Utilities.validationsList(vtUsuarioRols) == true) { 
				throw new ZMessManager().new DeletingException("vtUsuarioRols");

			}

			entity.setActivo("N");
			vtUsuarioDAO.update(entity);

			log.debug("delete VtUsuario successful");
		} catch (Exception e) {
			log.error("delete VtUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateVtUsuario(VtUsuario entity) throws Exception {
		log.debug("updating VtUsuario instance");

		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("VtUsuario");
			}

			if (entity.getVtEmpresa() == null) {
				throw new ZMessManager().new ForeignException("vtEmpresa");
			}

			if (entity.getActivo() == null) {
				throw new ZMessManager().new EmptyFieldException("activo");
			}

			if ((entity.getActivo() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getActivo(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException("activo");
			}

			if (entity.getClave() == null) {
				throw new ZMessManager().new EmptyFieldException("clave");
			}

			if ((entity.getClave() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getClave(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("clave");
			}

			if (entity.getFechaCreacion() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"fechaCreacion");
			}

			if (entity.getLogin() == null) {
				throw new ZMessManager().new EmptyFieldException("login");
			}

			if ((entity.getLogin() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getLogin(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("login");
			}

			if (entity.getNombre() == null) {
				throw new ZMessManager().new EmptyFieldException("nombre");
			}

			if ((entity.getNombre() != null) &&
					(Utilities.checkWordAndCheckWithlength(entity.getNombre(),
							255) == false)) {
				throw new ZMessManager().new NotValidFormatException("nombre");
			}

			if (entity.getUsuCreador() == null) {
				throw new ZMessManager().new EmptyFieldException("usuCreador");
			}

			if (entity.getUsuaCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException("usuaCodigo");
			}

			if (entity.getVtEmpresa().getEmprCodigo() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"emprCodigo_VtEmpresa");
			}

			vtUsuarioDAO.update(entity);

			log.debug("update VtUsuario successful");
		} catch (Exception e) {
			log.error("update VtUsuario failed", e);
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<VtUsuarioDTO> getDataVtUsuario() throws Exception {
		try {
			List<VtUsuario> vtUsuario = vtUsuarioDAO.findAll();

			List<VtUsuarioDTO> vtUsuarioDTO = new ArrayList<VtUsuarioDTO>();

			for (VtUsuario vtUsuarioTmp : vtUsuario) {
				VtUsuarioDTO vtUsuarioDTO2 = new VtUsuarioDTO();

				vtUsuarioDTO2.setUsuaCodigo(vtUsuarioTmp.getUsuaCodigo());
				vtUsuarioDTO2.setActivo((vtUsuarioTmp.getActivo() != null)
						? vtUsuarioTmp.getActivo() : null);
				vtUsuarioDTO2.setClave((vtUsuarioTmp.getClave() != null)
						? vtUsuarioTmp.getClave() : null);
				vtUsuarioDTO2.setFechaCreacion(vtUsuarioTmp.getFechaCreacion());
				vtUsuarioDTO2.setFechaModificacion(vtUsuarioTmp.getFechaModificacion());
				vtUsuarioDTO2.setLogin((vtUsuarioTmp.getLogin() != null)
						? vtUsuarioTmp.getLogin() : null);
				vtUsuarioDTO2.setNombre((vtUsuarioTmp.getNombre() != null)
						? vtUsuarioTmp.getNombre() : null);
				vtUsuarioDTO2.setUsuCreador((vtUsuarioTmp.getUsuCreador() != null)
						? vtUsuarioTmp.getUsuCreador() : null);
				vtUsuarioDTO2.setUsuModificador((vtUsuarioTmp.getUsuModificador() != null)
						? vtUsuarioTmp.getUsuModificador() : null);
				vtUsuarioDTO2.setEmprCodigo_VtEmpresa((vtUsuarioTmp.getVtEmpresa()
						.getEmprCodigo() != null)
						? vtUsuarioTmp.getVtEmpresa().getEmprCodigo() : null);
				vtUsuarioDTO.add(vtUsuarioDTO2);
			}

			return vtUsuarioDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public VtUsuario getVtUsuario(Long usuaCodigo) throws Exception {
		log.debug("getting VtUsuario instance");

		VtUsuario entity = null;

		try {
			entity = vtUsuarioDAO.findById(usuaCodigo);
		} catch (Exception e) {
			log.error("get VtUsuario failed", e);
			throw new ZMessManager().new FindingException("VtUsuario");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<VtUsuario> findPageVtUsuario(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
					throws Exception {
		List<VtUsuario> entity = null;

		try {
			entity = vtUsuarioDAO.findPage(sortColumnName, sortAscending,
					startRow, maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtUsuario Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberVtUsuario() throws Exception {
		Long entity = null;

		try {
			entity = vtUsuarioDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("VtUsuario Count");
		} finally {
		}

		return entity;
	}

	/**
	 *
	 * @param varibles
	 *            este arreglo debera tener:
	 *
	 * [0] = String variable = (String) varibles[i]; representa como se llama la
	 * variable en el pojo
	 *
	 * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
	 * valor necesita o no ''(comillas simples)usado para campos de tipo string
	 *
	 * [2] = Object value = varibles[i + 2]; representa el valor que se va a
	 * buscar en la BD
	 *
	 * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
	 * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
	 * en este campo iria el tipo de comparador que quiero si es = o <>
	 *
	 * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
	 * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
	 * que se le ingresen en los otros 4
	 *
	 *
	 * @param variablesBetween
	 *
	 * la diferencia son estas dos posiciones
	 *
	 * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
	 * a ser buscada en un rango
	 *
	 * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
	 *
	 * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
	 * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
	 *
	 * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
	 * ejemplo: a comparator1 1 and a < 5
	 *
	 * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
	 * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
	 * 5) *
	 * @param variablesBetweenDates(en
	 *            este caso solo para mysql)
	 *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
	 *            una fecha
	 *
	 * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
	 * dates)
	 *
	 * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
	 * dates)
	 *
	 * esto hace un between entre las dos fechas.
	 *
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<VtUsuario> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
					throws Exception {
		List<VtUsuario> list = new ArrayList<VtUsuario>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null) &&
						(variables[i + 2] != null) &&
						(variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " \'" +
										value + "\' )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0)
								? ("(model." + variable + " " + comparator + " " +
										value + " )")
										: (tempWhere + " AND (model." + variable + " " +
												comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null) &&
						(variablesBetween[j + 1] != null) &&
						(variablesBetween[j + 2] != null) &&
						(variablesBetween[j + 3] != null) &&
						(variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0)
							? ("(" + value + " " + comparator1 + " " + variable +
									" and " + variable + " " + comparator2 + " " + value2 +
									" )")
									: (tempWhere + " AND (" + value + " " + comparator1 +
											" " + variable + " and " + variable + " " +
											comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null) &&
						(variablesBetweenDates[k + 1] != null) &&
						(variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0)
							? ("(model." + variable + " between \'" + value +
									"\' and \'" + value2 + "\')")
									: (tempWhere + " AND (model." + variable +
											" between \'" + value + "\' and \'" + value2 + "\')");
				}

				k = k + 2;
			}
		}

		if (tempWhere.length() == 0) {
			where = null;
		} else {
			where = "(" + tempWhere + ")";
		}

		try {
			list = vtUsuarioDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = true)
	public VtUsuario findUsuarioByLogin(String login) throws Exception {
		List<VtUsuario> vtUsuarios= new ArrayList<VtUsuario>();

		try {
			Object[] variables = {"login", true, login, "="};
			
			

			vtUsuarios = findByCriteria(variables, null, null);

		} catch (Exception e) {
			log.info(e.getMessage(), e);
			throw new Exception("No se encontro el usuario con ese login");
		}


		return (vtUsuarios!=null && !vtUsuarios.isEmpty()
				? vtUsuarios.get(0) : null);
	}

	@Transactional(readOnly = true)
	public boolean autenticarUsuario(String login, String password) throws Exception {
		try {
			VtUsuario usuarioEncontrado = null;

			//Se busca al usuario po su login
			usuarioEncontrado = findUsuarioByLogin(login);

			//Si no lo encuentra arroja excepcion
			if (usuarioEncontrado == null){
				throw new Exception("El login especificado no se encuentra registrado");
			}
			
			//Si no lo encuentra arroja excepcion
			if (usuarioEncontrado.getActivo().equals("N")){
				throw new Exception("El Usuario especificado no se encuentra activo");
			}

			//Si lo encuentra pero no coincide ellogin arroja excepcion
//			if(!usuarioEncontrado.getLogin().equals(login)){
//				throw new Exception("El login no coincide con el del usuario");
//			}
			//Si lo encuentra pero no coincide la clave arroja excepcion
			if(!usuarioEncontrado.getClave().equals(password)){
				throw new Exception("El login o contraseña son incorrectos");
			}			

			return true;

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<VtUsuario> getVtUsuarioNoAsignados(VtProyecto proyecto) throws Exception {

		List<VtUsuario> usuariosSource = getVtUsuarioDesarrolladores();		
		List<VtProyectoUsuario> proyectosUsuarios = proyectoUsuarioLogic.findProyectoUsuarioPorProyecto(proyecto);

		if (proyectosUsuarios != null) {

			for (VtProyectoUsuario vtProyectoUsuario : proyectosUsuarios) {
				if(vtProyectoUsuario.getActivo().equals("S"))
					usuariosSource.remove(vtProyectoUsuario.getVtUsuario());
			}
		}


		return usuariosSource;
	}


	@Transactional(readOnly = true)
	public List<VtUsuario> getVtUsuarioAsignados(VtProyecto proyecto) throws Exception {

		List<VtUsuario> usuariosSource = getVtUsuarioDesarrolladores();

		List<VtProyectoUsuario> proyectosUsuarios = proyectoUsuarioLogic.findProyectoUsuarioPorProyecto(proyecto);
		List<VtUsuario> usuariosTarget= new ArrayList<>();

		if (proyectosUsuarios != null) {
			for (VtUsuario vtUsuario : usuariosSource) {
				for (VtProyectoUsuario vtProyectoUsuario : proyectosUsuarios) {
					if (vtUsuario.getLogin().equals(vtProyectoUsuario.getVtUsuario().getLogin()) == true && vtProyectoUsuario.getActivo().equals("S")) {
						usuariosTarget.add(vtUsuario);
					}
				}
			}

		}

		return usuariosTarget;
	}

	@Transactional(readOnly = true)
	public List<VtUsuario> getVtUsuarioDesarrolladores() throws Exception {
		List<VtUsuario> losDesarrolladores = new ArrayList<VtUsuario>();

		VtRol vtRol = vtRolLogic.getVtRol(2L);		
		List<VtUsuarioRol> usuarioRol = vtUsuarioRolLogic.findUsuarioRolbyRol(vtRol);		

		for (VtUsuarioRol vtUsuarioRol : usuarioRol) {
			if(vtUsuarioRol.getActivo().equals("S")){
				losDesarrolladores.add(vtUsuarioRol.getVtUsuario());
			}

		}

		return losDesarrolladores;
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void recuperarContrasena(VtUsuario vtUsuario) throws Exception{
	
			String clave = Utilities.getPassword();
			
			vtUsuario.setClave(clave);
			
			String asunto = "Reestablecer la contraseña de VobiTeam";
			
			String cuerpo = "Sr@, se ha solicitado un cambio de contraseña " + '\n' + '\n' +
							"Su nueva contraseña es= " + clave + '\n' + '\n'  + '\n' 
							+ "Por favor ingrese a VobiTeam lo más pronto posible y haga el cambio "
							+ "de ella" + '\n' + '\n'  
							+ "Gracias por su atención.";
			
			vtUsuarioDAO.update(vtUsuario);
			
			mailService.send(vtUsuario.getLogin(), asunto, cuerpo);
	}
	
	@Transactional(readOnly = true)
	public void nuevoUsuario(VtUsuario vtUsuario) throws Exception{
			
			String asunto = "Bienvenido a VobiTeam";
			
			String cuerpo = "Sr@," + vtUsuario.getNombre() +" le damos la bienvenida a VobiTeam " + '\n' 
							+ "Esperamos que la aplicación sea de su agrado, momentaneamente su cuenta es: " + '\n' + '\n' +
							"Usuario o Login= " + vtUsuario.getLogin() + '\n' +
							"Contraseña= " + vtUsuario.getClave() + '\n' + '\n' 
							+ "Perteneciente a la empresa " + vtUsuario.getVtEmpresa().getNombre() 
							+ '\n' + '\n'  
							+ "Por motivos de seguridad trate de ingresar lo mas pronto posible "
							+ "y hacer cambio de su contraseña." + '\n'
							+ "Le deseamos un excelente día." + '\n'  + '\n' ;
			
			
			mailService.send(vtUsuario.getLogin(), asunto, cuerpo);
	}
	
	@Transactional(readOnly = true)
	public boolean verificarContraseña(VtUsuario vtUsuario, String passActual ,String password1, String password2){	
		
		if (vtUsuario.getClave().equals(passActual)) {
			if (password1.equals(password2)) {
				return true;
			}
			else {
				return false;
			}
		}else {
			return false;
		}
		
				
	}
	
}
