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

    private ErrorContext(Builder builder) {
        this.log = builder.log; this.artifactsDir = builder.artifactsDir; this.page = builder.page;
        this.testName = builder.testName; this.timestamp = builder.timestamp;
    }
    public static class Builder {
        private Logger log;
        private Path artifactsDir;
        private Page page;
        private String testName = "test";
        private Instant timestamp = Instant.now();
        public Builder log(Logger l){ this.log=l; return this; }
        public Builder artifacts(Path p){ this.artifactsDir=p; return this; }
        public Builder page(Page p){ this.page=p; return this; }
        public Builder testName(String t){ this.testName=t; return this; }
        public ErrorContext build(){ return new ErrorContext(this); }
    }
}
