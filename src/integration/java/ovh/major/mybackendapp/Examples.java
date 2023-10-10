package ovh.major.mybackendapp;

public enum Examples {
    BAD_CREDENTIALS("{\"name\":\"badusername\",\"password\":\"badpass\"}"),
    CORRECT_CREDENTIALS("{\n" +
            "\"name\":\"user\",\n" +
            "\"password\":\"pass\"" +
            "}"),
    POST("{\n" +
            "    \"publicationDate\":\"2023-10-08T00:00:00.000+00:00\",\n" +
            "    \"paragraphs\": [        \n" +
            "        {            \n" +
            "            \"tag\": {\n" +
            "                    \"id\":\"\",\n" +
            "                    \"opening\": \"<h3 class=\\\"blog__h3\\\">\",\n" +
            "                    \"closing\": \"</h3>\"\n" +
            "                },\n" +
            "            \"paragraphContent\": \"Pierwsze testy integracyjne\"\n" +
            "        },        \n" +
            "        {            \n" +
            "            \"tag\": {\n" +
            "                    \"id\":\"\",\n" +
            "                    \"opening\": \"<p>\",\n" +
            "                    \"closing\": \"</p>\"\n" +
            "                },\n" +
            "            \"paragraphContent\": \"Czas już było wrócić do mojego backendu do bloga.\" \n" +
            "        }\n" +
            "    ]\n" +
            "}"),
    PARAGRAPH("{\n" +
            "            \"tag\": {\n" +
            "                    \"id\":\"\",\n" +
            "                    \"opening\": \"<h3 class=\\\"blog__h3\\\">\",\n" +
            "                    \"closing\": \"</h3>\"\n" +
            "                },\n" +
            "            \"paragraphContent\": \"Pierwsze testy integracyjne\"\n" +
            "}"),
    TAG("{\n" +
            "                    \"id\":\"\",\n" +
            "                    \"opening\": \"<h3 class=\\\"blog__h3\\\">\",\n" +
            "                    \"closing\": \"</h3>\"\n" +
            "}");


    private final String value;

    Examples(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}