package com.leonardo.titanic.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class PassengerControllerTest(
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun `should return paginated passengers`() {
        mockMvc.get("/passengers")
            .andExpect {
                status { isOk() }
                jsonPath("$.content") { isArray() }
                jsonPath("$.content.length()") { value(3) }
                jsonPath("$.page") { value(0) }
                jsonPath("$.size") { value(10) }
            }
    }

    @Test
    fun `should filter by survived = 1`() {
        mockMvc.get("/passengers?survived=1")
            .andExpect {
                status { isOk() }
                jsonPath("$.content.length()") { value(2) }
                jsonPath("$.content[0].survived") { value(1) }
            }
    }

    @Test
    fun `should filter by pclass = 1`() {
        mockMvc.get("/passengers?pclass=1")
            .andExpect {
                status { isOk() }
                jsonPath("$.content.length()") { value(1) }
                jsonPath("$.content[0].pclass") { value(1) }
            }
    }

    @Test
    fun `should filter by survived and pclass`() {
        mockMvc.get("/passengers?survived=1&pclass=1")
            .andExpect {
                status { isOk() }
                jsonPath("$.content.length()") { value(1) }
            }
    }

    @Test
    fun `should return passenger by id`() {
        mockMvc.get("/passengers/1")
            .andExpect {
                status { isOk() }
                jsonPath("$.passengerId") { value(1) }
            }
    }

    @Test
    fun `should return 404 when passenger not found`() {
        mockMvc.get("/passengers/999")
            .andExpect {
                status { isNotFound() }
            }
    }

    @Test
    fun `should limit page size to 100`() {
        mockMvc.get("/passengers?page=0&size=500")
            .andExpect {
                status { isOk() }
                jsonPath("$.size") { value(100) }
            }
    }
}
