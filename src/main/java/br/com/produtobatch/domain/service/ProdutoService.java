package br.com.produtobatch.domain.service;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class ProdutoService {

    private final Path fileStorageLocation;
    private final JobLauncher jobLauncher;
    private final Job job;

    public ProdutoService(@Value("${file.upload-dir}") String fileUploadDir,
                          @Qualifier("jobLauncherAsync") JobLauncher jobLauncher,
                          Job job) {
        this.fileStorageLocation = Paths.get(fileUploadDir);
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void uploadProdutoFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = fileStorageLocation.resolve(fileName);
        file.transferTo(targetLocation);

        JobParameters jobParameters = new JobParametersBuilder()
                .addJobParameter("produtos", file.getOriginalFilename(), String.class, false)
                .addJobParameter("produtosFile", "file:" + targetLocation, String.class)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }

    @Scheduled(fixedRate = 30000)
    public void processarProdutosAgendados() {
        try {
            Files.list(fileStorageLocation).forEach(filePath -> {
                JobParameters jobParameters = new JobParametersBuilder()
                        .addJobParameter("produtosFile", "file:" + filePath, String.class, false)
                        .addDate("date", new Date())
                        .toJobParameters();


                try {
                    jobLauncher.run(job, jobParameters);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException e) {
            System.out.println("Erro ao processar arquivos agendados");
        }
    }


}
