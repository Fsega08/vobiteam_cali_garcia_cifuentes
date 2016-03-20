package com.vobi.team.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Zathura Code Generator http://zathuracode.org/
 * www.zathuracode.org
 * 
 */
public class ZMessManager extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(ZMessManager.class);

	public final static String ALL = "All ";
	public final static String ENTCHILD = "related tables(childs)";
	public final static String FOREIGNDATA = "foreign classes data: ";
	public static String ENTITY_SUCCESFULLYSAVED = "La entidad se ha guardado exitosamente";
	public static String ENTITY_SUCCESFULLYDELETED = "La entidad se ha eliminado exitosamente";
	public static String ENTITY_SUCCESFULLYMODIFIED = "La entidad se ha actualizado exitosamente";
	public static String ENTITY_WITHSAMEKEY = "Se ha encontrado otra entidad con el mismo ID";
	public static String ENTITY_NOENTITYTOUPDATE = "No se ha encontrado ninguna entidad, con ese ID ";

	public ZMessManager() {
	}

	public ZMessManager(String exception) {
		super(exception);
	}

	public class NotValidFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFieldException(String info) {
			super("El valor del campo: \"" + info + "\" no es valido");
		}
	}
	
	public class NullEntityExcepcion extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NullEntityExcepcion(String info) {
			super("La " + info + " de la entidad no puede ser nulo o vacio");
		}
	}

	public class EmptyFieldException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public EmptyFieldException(String info) {
			super("El valor del campo: \"" + info
					+ "\" no puede ser nulo o vacío");
		}
	}

	public class NotValidFormatException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public NotValidFormatException(String info) {
			super("El formato o la longitud del campo: \"" + info
					+ "\" no es valido");
		}
	}

	public class DeletingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public DeletingException(String info) {
			super("La entidad que se intenta eliminar "
					+ "puede tener informacion relacionada, "
					+ "antes de intentar de nuevo, "
					+ "revisa la informacion de la entidad, \"" + info+"\"");
		}
	}
	
	public class ForeignException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public ForeignException(String info) {
			super("No hay datos relacionados con el campo \"" + info+ "\"");
		}
	}	

	public class GettingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public GettingException(String info) {
			super("Hubo una excepcion intentando obtener " + info);
		}
	}

	public class FindingException extends ZMessManager {
		private static final long serialVersionUID = 1L;

		public FindingException(String info) {
			super("Hubo una excepcion intentando encontar: " + info);
		}
	}

}
