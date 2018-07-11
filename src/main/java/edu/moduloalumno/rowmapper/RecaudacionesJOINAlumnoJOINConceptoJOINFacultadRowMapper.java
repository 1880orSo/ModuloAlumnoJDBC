package edu.moduloalumno.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import edu.moduloalumno.entity.RecaudacionesJOINAlumnoJOINConceptoJOINFacultad;

public class RecaudacionesJOINAlumnoJOINConceptoJOINFacultadRowMapper implements RowMapper<RecaudacionesJOINAlumnoJOINConceptoJOINFacultad> {
	@Override
	public RecaudacionesJOINAlumnoJOINConceptoJOINFacultad mapRow(ResultSet row, int rowNum) throws SQLException {
		RecaudacionesJOINAlumnoJOINConceptoJOINFacultad recaudacionesJOINAlumnoJOINConceptoJOINFacultad = new RecaudacionesJOINAlumnoJOINConceptoJOINFacultad();
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setIdRec(row.getInt("id_rec"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setIdAlum(row.getInt("id_alum"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setApeNom(row.getString("ape_nom"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setConcepto(row.getString("concepto"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setNumero(row.getString("numero"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setNombre(row.getString("nombre"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setMoneda(row.getString("moneda"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setImporte(row.getDouble("importe"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setFecha(row.getDate("fecha"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setIdPrograma(row.getInt("id_programa"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setCodAlumno(row.getString("cod_alumno"));
		recaudacionesJOINAlumnoJOINConceptoJOINFacultad.setObservacion(row.getString("observacion"));
		return recaudacionesJOINAlumnoJOINConceptoJOINFacultad;
	}
}







