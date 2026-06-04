package errors;

import com.microsoft.playwright.Page;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.time.Instant;

public class ErrorContext {
    public final Logger log;
    public final Path artifactsDir;
    public final Page page;
    public final String testName;
    public final Instant timestamp;

    public ErrorContext(Builder builder) {
        this.log = builder.log;
        this.artifactsDir = builder.artifactsDir;
        this.page = builder.page;
        this.testName = builder.testName;
        this.timestamp = builder.timestamp;
    }
    public static class Builder {
        private Logger log;
        private Path artifactsDir;
        private Page page;
        private String testName = "test";
        private final Instant timestamp = Instant.now();
        public Builder log(Logger logger){ this.log=logger; return this; }
        public Builder artifacts(Path path){ this.artifactsDir=path; return this; }
        public Builder page(Page page){ this.page=page; return this; }
        public Builder testName(String testName){ this.testName=testName; return this; }
        public ErrorContext build(){ return new ErrorContext(this); }
    }
}
