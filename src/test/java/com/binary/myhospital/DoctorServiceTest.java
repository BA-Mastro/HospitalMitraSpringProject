//package com.binary.myhospital;
//
//import com.binary.myhospital.entities.Department;
//import com.binary.myhospital.entities.Doctor;
//import com.binary.myhospital.repositories.DoctorRepository;
//import com.binary.myhospital.services.DepartmentService;
//import com.binary.myhospital.services.DoctorService;
//import com.binary.myhospital.services.DoctorServiceImpl;
//import org.aspectj.lang.annotation.After;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.commons.logging.Logger;
//import org.junit.platform.commons.logging.LoggerFactory;
//import org.mockito.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//public class DoctorServiceTest {
//    private static final Logger log = LoggerFactory.getLogger(DoctorServiceTest.class);
//    @InjectMocks
//    private DoctorServiceImpl doctorService;
//    @Mock
//    private DoctorRepository doctorRepository;
//    private AutoCloseable closeable;
//    @Captor
//    private ArgumentCaptor<Doctor> doctorCaptor;
//
//    @BeforeEach
//    void setup() throws Exception{
//        closeable = MockitoAnnotations.openMocks(this);
//    }
//    @AfterEach
//    void takedown() throws Exception{
//        closeable.close();
//
//    }
//
//    @Test
//    void carServiceImpl_createDoctor_shouldSucceed(){
//        Department depart = new Department();
//        depart.setDepartmentId(1L);
//        Doctor databaseDoctor = new Doctor(1L,"John","Miller",5012234567L,"johnM@gmail.com","Surgeon",depart);
//
//        Doctor newDoctor = new Doctor(1L,"John","Miller",5012234567L,"johnM@gmail.com","Surgeon",depart);
//
//        Mockito.when(doctorRepository.save(newDoctor)).thenReturn(databaseDoctor);
//        Doctor result = doctorService.createDoctor(newDoctor);
//        Mockito.verify(doctorRepository,Mockito.times(1)).save(newDoctor);
//        assertThat(result).isEqualTo(databaseDoctor);
//
//    }
//
//
//}
