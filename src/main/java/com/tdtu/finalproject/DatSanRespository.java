//package com.tdtu.finalproject;
//
//import com.tdtu.finalproject.model.DatSan;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//public interface DatSanRespository extends CrudRepository<DatSan, Long> {
//    @Query("SELECT datSan FROM DatSan datSan WHERE datSan.id = :id")
//    public DatSan getDatSanById(@Param("id") int id);
//}
