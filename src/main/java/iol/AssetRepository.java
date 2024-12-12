package iol;

public enum AssetRepository {
    AL30("ARG", "Titulos Publicos"),
    AL35("ARG", "Titulos Publicos"),
    AE38("ARG", "Titulos Publicos"),
    S31O4("ARG", "Titulos Publicos"),
    S31M5("ARG", "Titulos Publicos"),
    S31E5("ARG", "Titulos Publicos"),
    S28F5("ARG", "Titulos Publicos"),
    S28A5("ARG", "Titulos Publicos"),
    YPFD("ARG", "Energy"),
    TGSU2("ARG", "Energy"),
    TGNO4("ARG", "Energy"),
    PAMP("ARG", "Energy"),
    LOMA("ARG", "Construction"),
    GGAL("ARG", "Banking"),
    UBER("USA", "Automotive"),
    QQQ("USA", "Technology"),
    MELI("ARG", "Technology"),
    GLOB("ARG", "Technology"),
    BA("USA", "Aerospace"),
    AAPL("USA", "Technology"),
    AMZN("USA", "Technology"),
    BBD("BRA", "Banking"),
    C("USA", "Banking"),
    DISN("USA", "Entertainment"),
    FERR("ARG", "Construction"),
    BABA("CHINA", "Conglomerate"),
    GOOGL("USA", "Technology"),
    META("USA", "Technology"),
    MMM("USA", "Conglomerate"),
    MRK("GERMANY", "Healthcare"),
    PFE("USA", "Healthcare"),
    PG("USA", "Consumer Goods"),
    XOM("USA", "Energy"),
    CVX("USA", "Energy"),
    IBM("USA", "Technology"),
    JNJ("USA", "Healthcare"),
    MO("USA", "Consumer Goods"),
    MSFT("USA", "Technology"),
    NKE("USA", "Consumer Goods"),
    NVDA("USA", "Technology"),
    TSM("SOUTH KOREA", "Technology"),
    SPY("USA", "Banking"),
    T("USA", "Telecommunications"),
    WBA("USA", "Healthcare"),
    X23N3("ARG", "Banking"),
    BRKB("USA", "Banking"),
    JPM("USA", "Banking"),
    TSLA("USA", "Automotive"),
    UL("UK", "Consumer Goods"),
    JD("CHINA", "E-commerce"),
    NIO("CHINA", "Automotive");

    private final String country;
    private final String industry;

    AssetRepository(String country, String industry) {
        this.country = country;
        this.industry = industry;
    }

    public String getCountry() {
        return country;
    }

    public String getIndustry() { return industry; }
}
