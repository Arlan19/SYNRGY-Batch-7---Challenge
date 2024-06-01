package com.allacsta.latihan.controller;

import com.allacsta.latihan.controller.fileupload.UploadMerchantResponse;
import com.allacsta.latihan.entity.Merchant;
import com.allacsta.latihan.repository.MerchantRepository;
import com.allacsta.latihan.service.MerchantService;
import com.allacsta.latihan.service.PDFService;
import com.allacsta.latihan.utils.Response;
import javax.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;



@RestController
@RequestMapping("v1/merchant")
public class MerchantController {

    @Autowired
    public MerchantRepository merchantRepository;
    @Autowired
    public MerchantService merchantService;

    @Autowired
    public PDFService pdfService;

    @Autowired
    public Response response;

    private String UPLOADED_FOLDER;
    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @GetMapping(value = {"/list-merchant","/list-merchant/"})
    public ResponseEntity<Map> getListMerchant(){
        return new ResponseEntity<Map>(response.sukses(merchantService.pagination(0, 10)), HttpStatus.OK);
    }

    @PostMapping(value = {"/save", "/save/"})
    public Map saveMerchant(@RequestBody Merchant request){
        Map map = new HashMap();
        try {
            logger.info("Request",request);
            map = merchantService.save(request);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return map;
        }
    }

    @PutMapping(value = {"/update/{id}","/update/{id}/"})
    public Map updateMerchant(@PathVariable UUID id, @RequestBody Merchant request){
        Map map = new HashMap();
        try {
            request.setId(id);
            logger.info("Request",request);
            map = merchantService.edit(request);
            return map;
        }catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return map;
        }

    }

    @DeleteMapping(value = {"/deleted", "/deleted/"})
    public ResponseEntity<Map> deleteMerchant(@RequestBody Merchant request) {
        return new ResponseEntity<Map>(response.sukses(request), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/deleted/{id}", "/deleted/{id}"})
    public Map deleteMerchantById(@PathVariable UUID id) {
        Map map = new HashMap();
        try {
            map = merchantService.deleteById(id);
            return map;
        } catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return response.error("An error occurred while deleting the merchant.", 500);
        }
    }

    @GetMapping(value = {"/getbyid/{id}", "/getbyid/{id}/"})
    public Map getById(@PathVariable UUID id){
        Map map = new HashMap();
        try{
            map = merchantService.getById(id);
            return map;
        } catch (Exception e){
            logger.info("Error : ", e.getMessage());
            return response.error("An error occurred while get the merchant.", 500);
        }
    }

    @GetMapping(value = { "/list-merchant-pagination", "/list-merchant-pagination/" })
    public ResponseEntity<Map> list(@RequestParam(required = false, name = "merchant_name") String merchant_name,
                                    @RequestParam(required = false, name = "merchant_location") String merchant_location,
                                    @RequestParam(required = false, name = "open") Boolean open,
                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {


        Specification<Merchant> spec = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (merchant_name != null && !merchant_name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("merchant_name")), "%" + merchant_name.toLowerCase() + "%"));
            }
            if (merchant_location != null && !merchant_location.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("merchant_location")), "%" + merchant_location.toLowerCase() + "%"));
            }
            if (open != null ) {
                predicates.add(criteriaBuilder.equal(root.get("open"), open));
            }

//            Aaa = Aaa
            // AaaA=aAaA
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        Page<Merchant> clientCompanyList = merchantRepository.findAll(spec, pageable);
        return new ResponseEntity<Map>(response.sukses(clientCompanyList), HttpStatus.OK);
    }

    @GetMapping(value = {"/download-merchant-pdf", "/download-merchant-pdf/"})
    public ResponseEntity<byte[]> downloadMerchantPdf() {
        byte[] pdfData = pdfService.generatePDF();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=merchant_report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfData);
    }

//    @RequestMapping(value = "/v1/uploadMerchant", method = RequestMethod.POST, consumes = {"multipart/form-data", "application/json"})
//    public ResponseEntity<Map> uploadMerchantResponse(@RequestParam("file") MultipartFile file,
//                                                         @RequestParam("merchant") Merchant merchant) throws IOException {
//
//        Map map = new HashMap();
//        if (file.isEmpty()) {
//            return new UploadMerchantResponse(null, null,
//                    null, false, "File is empty");
//        }
//
//        try {
//            UUID fileId = UUID.randomUUID();
//            String fileExtension = getFileExtension(file.getOriginalFilename());
//            String fileName = UPLOADED_FOLDER + fileId + fileExtension;
//            Path filePath = Paths.get(fileName);
//
//            Files.copy(file.getInputStream(), filePath);
//
//            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/v1/showFile/")
//                    .path(fileId + fileExtension)
//                    .toUriString();
//
//            logger.info("Request",merchant);
//
//            map = merchantService.save(merchant);
//
//            return map;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new UploadMerchantResponse(null, null,
//                    null, false, "Failed to upload file: " + e.getMessage());
//        }
//
//
//
////        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
////                .path("/v1/showFile/")
////                .path(fileNameforDOwnload)
////                .toUriString();
////
////
////        return new UploadFileResponse(fileNameforDOwnload, fileDownloadUri,
////                file.getContentType(), file.getSize(), "false");
//    }
//
//    private String getFileExtension(String fileName) {
//        int dotIndex = fileName.lastIndexOf(".");
//        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
//            return fileName.substring(dotIndex);
//        }
//        return ""; // Default to empty string if no extension found
//    }
}

//
//@RestController
//@RequestMapping("v1/merchant")
//public class MerchantController {
//
//    @Autowired
//    public MerchantRepository merchantRepository;
//    @Autowired
//    public MerchantService merchantService;
//
//    @Autowired
//    public Response response;
//
//    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);
//
//    @GetMapping(value = {"/list-merchant","/list-merchant/"})
//    public ResponseEntity<Map> getListMerchant(){
//        return new ResponseEntity<Map>(response.sukses(merchantService.pagination(0, 10)), HttpStatus.OK);
//    }
//
//    @PostMapping(value = {"/save", "/save/"})
//    public Map saveMerchant(@RequestBody Merchant request){
//        Map map = new HashMap();
//        try {
//            logger.info("Request",request);
//            map = merchantService.save(request);
//            return map;
//        }catch (Exception e){
//            logger.info("Error : ", e.getMessage());
//            return map;
//        }
//    }
//
////    @PutMapping(value = {"/update","/update/"})
////    public ResponseEntity<Map> updateMerchant(@RequestBody Merchant request){
////        return new ResponseEntity<Map>(response.sukses(request), HttpStatus.OK);
////    }
//
//    @PutMapping(value = {"/update/{id}","/update/{id}/"})
//    public Map updateMerchant(@PathVariable UUID id, @RequestBody Merchant request){
//        Map map = new HashMap();
//        try {
//            request.setId(id);
//            logger.info("Request",request);
//            map = merchantService.edit(request);
//            return map;
//        }catch (Exception e){
//            logger.info("Error : ", e.getMessage());
//            return map;
//        }
//
//    }
//
//    @DeleteMapping(value = {"/deleted", "/deleted/"})
//    public ResponseEntity<Map> deleteMerchant(@RequestBody Merchant request) {
//        return new ResponseEntity<Map>(response.sukses(request), HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = {"/deleted/{id}", "/deleted/{id}"})
//    public ResponseEntity<Map> deleteMerchantById(@PathVariable UUID id) {
//        return new ResponseEntity<Map>(response.sukses(id), HttpStatus.OK);
//    }
//
//    @GetMapping(value = { "/list-merchant-pagination", "/list-merchant-pagination/" })
//    public ResponseEntity<Map> list(@RequestParam(required = false, name = "merchant_name") String merchant_name,
//                                    @RequestParam(required = false, name = "merchant_location") String merchant_location,
//                                    @RequestParam(required = false, name = "open") Boolean open,
//                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {
//
//
//        Specification<Merchant> spec = ((root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if (merchant_name != null && !merchant_name.isEmpty()) {
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("merchant_name")), "%" + merchant_name.toLowerCase() + "%"));
//            }
//            if (merchant_location != null && !merchant_location.isEmpty()) {
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("merchant_location")), "%" + merchant_location.toLowerCase() + "%"));
//            }
//            if (open != null ) {
//                predicates.add(criteriaBuilder.equal(root.get("open"), open));
//            }
//
////            Aaa = Aaa
//            // AaaA=aAaA
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        });
//
//        Page<Merchant> clientCompanyList = merchantRepository.findAll(spec, pageable);
//        return new ResponseEntity<Map>(response.sukses(clientCompanyList), HttpStatus.OK);
//    }
//}
