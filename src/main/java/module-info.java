/**
 * JFreePDF provides an implementation of the {@code Graphics2D} API that 
 * produces output in Adobe's Portable Document Format (PDF).
 */
module org.jfree.pdf {
    requires java.base;
    requires java.desktop;
    requires java.logging;
    exports org.jfree.pdf;
    exports org.jfree.pdf.font;
}
