package br.com.produtobatch.produtobatch;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

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
                .addJobParameter("produtos", file.getOriginalFilename(), String.class, true)
                .addJobParameter("produtosFile", "file:" + targetLocation, String.class).toJobParameters();

        jobLauncher.run(job, jobParameters);
    }


}
