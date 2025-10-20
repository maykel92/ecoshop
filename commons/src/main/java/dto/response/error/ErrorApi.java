package dto.response.error;

import java.util.List;

public record ErrorApi(TypeError typeError,
        String message,
        List<String>messages,
        String patch,
        int status) {

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        TypeError typeError;
        String message;
        List<String> messages = null;
        String patch;
        int status;

        public Builder typeError(TypeError typeError) {
            this.typeError =typeError;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder messages(List<String> messages) {
            this.messages = messages;
            return this;
        }

        public Builder patch(String patch) {
            this.patch = patch;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public ErrorApi build(){
            return new ErrorApi(typeError, message, messages, patch, status);
        }
    }

}



