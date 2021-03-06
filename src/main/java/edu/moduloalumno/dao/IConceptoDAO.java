package edu.moduloalumno.dao;

import java.util.List;

import edu.moduloalumno.entity.Concepto;

public interface IConceptoDAO {

	List<Concepto> getAllConceptos();
	
	List<Concepto> getConceptoIdByApeNom(String apeNom);
	
	List<Concepto> getConceptoIdByApeNomRestringido(String apeNom);
	
	List<Concepto> getConceptoIdByNombresApellidos(String nombres,String apellidos);

	Concepto getConceptoById(int idConcepto);

	void addConcepto(Concepto concepto);

	void updateConcepto(Concepto concepto);

	void deleteConcepto(int idConcepto);

}