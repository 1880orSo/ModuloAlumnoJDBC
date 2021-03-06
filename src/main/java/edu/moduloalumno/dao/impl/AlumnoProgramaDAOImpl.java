package edu.moduloalumno.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.moduloalumno.dao.IAlumnoProgramaDAO;
import edu.moduloalumno.entity.AlumnoPrograma;
import edu.moduloalumno.rowmapper.AlumnoProgramaRowMapper;

@Transactional
@Repository
public class AlumnoProgramaDAOImpl implements IAlumnoProgramaDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public AlumnoPrograma getAlumnoProgramaById(String codAlumno) {
		String sql = "SELECT cod_alumno, ape_paterno, ape_materno, nom_alumno, cod_especialidad, cod_tip_ingreso, cod_situ, cod_perm, anio_ingreso, dni_m, id_programa FROM alumno_programa WHERE cod_alumno = ?";
		RowMapper<AlumnoPrograma> rowMapper = new BeanPropertyRowMapper<AlumnoPrograma>(AlumnoPrograma.class);
		AlumnoPrograma alumnoPrograma = jdbcTemplate.queryForObject(sql, rowMapper, codAlumno);
		return alumnoPrograma;
	}

	@Override
	public List<AlumnoPrograma> getAllAlumnoProgramas() {
		String sql = "SELECT cod_alumno, ape_paterno, ape_materno, nom_alumno, cod_especialidad, cod_tip_ingreso, cod_situ, cod_perm, anio_ingreso, dni_m, id_programa FROM alumno_programa";
		// RowMapper<AlumnoPrograma> rowMapper = new
		// BeanPropertyRowMapper<AlumnoPrograma>(AlumnoPrograma.class);
		RowMapper<AlumnoPrograma> rowMapper = new AlumnoProgramaRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public List<AlumnoPrograma> getAlumnoProgramasIdByNombresApellidos(String nombresApellidos) {
		String sql = "SELECT ap.cod_alumno, ap.ape_paterno, ap.ape_materno, ap.nom_alumno, ap.cod_especialidad, ap.cod_tip_ingreso, ap.cod_situ, ap.cod_perm, ap.anio_ingreso, ap.dni_m, ap.id_programa FROM alumno_programa ap where ((ap.ape_paterno || ' ' || ap.ape_materno || ' ' || ap.nom_alumno) like '%' || ? || '%') or ((ap.nom_alumno || ' ' || ap.ape_paterno || ' ' || ap.ape_materno) like '%' || ? || '%')";
		// RowMapper<AlumnoPrograma> rowMapper = new
		// BeanPropertyRowMapper<AlumnoPrograma>(AlumnoPrograma.class);
		RowMapper<AlumnoPrograma> rowMapper = new AlumnoProgramaRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nombresApellidos, nombresApellidos);
	}
	
	@Override
	public List<AlumnoPrograma> getAlumnoProgramasIdByNombresApellidosRestringido(String nombresApellidos) {
		String sql = "SELECT ap.cod_alumno, ap.ape_paterno, ap.ape_materno, ap.nom_alumno, ap.cod_especialidad, ap.cod_tip_ingreso, ap.cod_situ, ap.cod_perm, ap.anio_ingreso, ap.dni_m, ap.id_programa FROM alumno_programa ap WHERE to_tsquery( ? ) @@ to_tsvector(coalesce(ap.nom_alumno,'') || ' ' ||coalesce(ap.ape_paterno,'') || ' ' ||coalesce(ap.ape_materno,''))";
		// RowMapper<AlumnoPrograma> rowMapper = new
		// BeanPropertyRowMapper<AlumnoPrograma>(AlumnoPrograma.class);
		RowMapper<AlumnoPrograma> rowMapper = new AlumnoProgramaRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, nombresApellidos);
	}


	@Override
	public void addAlumnoPrograma(AlumnoPrograma alumnoPrograma) {
		// Add alumnoPrograma
		String sql = "INSERT INTO alumno_programa (cod_alumno, ape_paterno, ape_materno, nom_alumno, cod_especialidad, cod_tip_ingreso, cod_situ, cod_perm, anio_ingreso, dni_m, id_programa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, alumnoPrograma.getCodAlumno(), alumnoPrograma.getApePaterno(),
				alumnoPrograma.getApeMaterno(), alumnoPrograma.getNomAlumno(), alumnoPrograma.getCodEspecialidad(),
				alumnoPrograma.getCodTipIngreso(), alumnoPrograma.getCodSitu(), alumnoPrograma.getCodPerm(),
				alumnoPrograma.getAnioIngreso(), alumnoPrograma.getDniM(), alumnoPrograma.getIdPrograma());

		// Fetch alumnoPrograma id
		sql = "SELECT cod_alumno FROM alumno_programa WHERE cod_alumno = ?";
		String codAlumno = jdbcTemplate.queryForObject(sql, String.class, alumnoPrograma.getCodAlumno());

		// Set alumnoPrograma id
		alumnoPrograma.setCodAlumno(codAlumno);
	}

	@Override
	public void updateAlumnoPrograma(AlumnoPrograma alumnoPrograma) {
		String sql = "UPDATE alumno_programa SET ape_paterno = ?, ape_materno = ?, nom_alumno = ?, cod_especialidad = ?, cod_tip_ingreso = ?, cod_situ = ?, cod_perm = ?, anio_ingreso = ?, dni_m = ?, id_programa = ? WHERE cod_alumno = ?";
		jdbcTemplate.update(sql, alumnoPrograma.getApePaterno(), alumnoPrograma.getApeMaterno(),
				alumnoPrograma.getNomAlumno(), alumnoPrograma.getCodEspecialidad(), alumnoPrograma.getCodTipIngreso(),
				alumnoPrograma.getCodSitu(), alumnoPrograma.getCodPerm(), alumnoPrograma.getAnioIngreso(),
				alumnoPrograma.getDniM(), alumnoPrograma.getIdPrograma(), alumnoPrograma.getCodAlumno());
	}

	@Override
	public void deleteAlumnoPrograma(String codAlumno) {
		String sql = "DELETE FROM alumno_programa WHERE cod_alumno = ?";
		jdbcTemplate.update(sql, codAlumno);
	}


}