package com.planning.taskplanning.controller;

import com.planning.taskplanning.service.FileService;
import com.planning.taskplanning.service.impl.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @PostMapping
    public ResponseEntity<?> create(Map<String, String> nomeTime) {
        if (Objects.nonNull(nomeTime)) {
            fileService.addTeamName(nomeTime);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nomeTime);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity FindByFile(@RequestParam(required = false) boolean exportJiraImporter,
                                     @RequestParam(required = false) boolean exportPlanningPoker,
                                     @RequestParam(required = false) boolean exportParameterizationFile) throws IOException {
        if (exportJiraImporter) {
            log.debug("REST request to export jiraImporter");
            String fileName = fileService.createFile(true);
            InputStreamResource inputStreamResource = fileService.returnJiraImporterTxt();
            try {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(FileServiceImpl.file.length())
                        .body(inputStreamResource);
            } finally {
                FileServiceImpl.file.delete();
            }
        }
        if (exportPlanningPoker) {
            log.debug("REST request to export planningPoker");
            String fileName = fileService.createFile(false);
            InputStreamResource inputStreamResource = fileService.returnPlanningPokerTxt();
            try {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(FileServiceImpl.file.length())
                        .body(inputStreamResource);
            } finally {
                FileServiceImpl.file.delete();
            }
        }
        if (exportParameterizationFile) {
            log.debug("REST request to export parametrization file");
            InputStreamResource inputStreamResource = fileService.returnParametrizationFile();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ArquivoParametrizacao")
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(FileServiceImpl.file.length())
                    .body(inputStreamResource);
        }
        return ResponseEntity.notFound().build();
    }
}
