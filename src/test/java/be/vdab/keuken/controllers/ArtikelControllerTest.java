package be.vdab.keuken.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/artikelgroepen.sql","/artikels.sql"})
class ArtikelControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
//    private final static String ARTIKELS = "artikels";
    private final MockMvc mockMvc;

    public ArtikelControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    private long idVanTest1Artikel (){ return jdbcTemplate.queryForObject("select id from artikels where naam = 'test1'", Long.class);}
    @Test
    void findById() throws Exception{
        mockMvc.perform(get("/artikels/{id}", idVanTest1Artikel()))
                .andExpectAll(status().isOk(),
                        jsonPath("naam").value("test1"));
    }
    @Test
    void findByOnbestaandeIdVindtGeenArtikel() throws Exception {
        mockMvc.perform(get("/artikels/{id}", Long.MAX_VALUE))
                .andExpect(
                        status().isNotFound());
    }
}