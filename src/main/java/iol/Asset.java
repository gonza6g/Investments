package iol;

public class Asset {
    private float cantidad;
    private float comprometido;
    private float puntosVariacion;
    private float variacionDiaria;
    private float ultimoPrecio;
    private float ppc;
    private float gananciaPorcentaje;
    private float gananciaDinero;
    private float valorizado;
    private String symbol;
    private String descripcion;
    private String pais;
    private String mercado;
    private String tipo;
    private String plazo;
    private String moneda;
    private Boolean parking;

    // custom properties
    private String industry;

    public Asset(float cantidad, float comprometido, float puntosVariacion, float variacionDiaria, float ultimoPrecio, float ppc, float gananciaPorcentaje, float gananciaDinero, float valorizado, String symbol, String descripcion, String pais, String mercado, String tipo, String plazo, String moneda, Boolean parking, String industry) {
        this.cantidad = cantidad;
        this.comprometido = comprometido;
        this.puntosVariacion = puntosVariacion;
        this.variacionDiaria = variacionDiaria;
        this.ultimoPrecio = ultimoPrecio;
        this.ppc = ppc;
        this.gananciaPorcentaje = gananciaPorcentaje;
        this.gananciaDinero = gananciaDinero;
        this.valorizado = valorizado;
        this.symbol = symbol;
        this.descripcion = descripcion;
        this.pais = pais;
        this.mercado = mercado;
        this.tipo = tipo;
        this.plazo = plazo;
        this.moneda = moneda;
        this.parking = parking;
        this.industry = industry;
    }

    public Asset() {
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getComprometido() {
        return comprometido;
    }

    public void setComprometido(float comprometido) {
        this.comprometido = comprometido;
    }

    public float getPuntosVariacion() {
        return puntosVariacion;
    }

    public void setPuntosVariacion(float puntosVariacion) {
        this.puntosVariacion = puntosVariacion;
    }

    public float getVariacionDiaria() {
        return variacionDiaria;
    }

    public void setVariacionDiaria(float variacionDiaria) {
        this.variacionDiaria = variacionDiaria;
    }

    public float getUltimoPrecio() {
        return ultimoPrecio;
    }

    public void setUltimoPrecio(float ultimoPrecio) {
        this.ultimoPrecio = ultimoPrecio;
    }

    public float getPpc() {
        return ppc;
    }

    public void setPpc(float ppc) {
        this.ppc = ppc;
    }

    public float getGananciaPorcentaje() {
        return gananciaPorcentaje;
    }

    public void setGananciaPorcentaje(float gananciaPorcentaje) {
        this.gananciaPorcentaje = gananciaPorcentaje;
    }

    public float getGananciaDinero() {
        return gananciaDinero;
    }

    public void setGananciaDinero(float gananciaDinero) {
        this.gananciaDinero = gananciaDinero;
    }

    public float getValorizado() {
        return valorizado;
    }

    public void setValorizado(float valorizado) {
        this.valorizado = valorizado;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Assets{" +
                "cantidad=" + cantidad +
                ", comprometido=" + comprometido +
                ", puntosVariacion=" + puntosVariacion +
                ", variacionDiaria=" + variacionDiaria +
                ", ultimoPrecio=" + ultimoPrecio +
                ", ppc=" + ppc +
                ", gananciaPorcentaje=" + gananciaPorcentaje +
                ", gananciaDinero=" + gananciaDinero +
                ", valorizado=" + valorizado +
                ", simbolo='" + symbol + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", pais='" + pais + '\'' +
                ", mercado='" + mercado + '\'' +
                ", tipo='" + tipo + '\'' +
                ", plazo='" + plazo + '\'' +
                ", moneda='" + moneda + '\'' +
                ", parking=" + parking +
                '}';
    }
}
