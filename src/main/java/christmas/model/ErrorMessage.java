package christmas.model;

public enum ErrorMessage {
    // 에러 메시지 추가하기
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}