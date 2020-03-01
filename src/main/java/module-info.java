/**
 * JFreePDF provides an implementation of the {@code Graphics2D} API that 
 * produces output in Adobe's Portable Document Format (PDF).
 * <p>For the latest information about JFreePDF, please refer to the project page
 * at GitHub: <a href="https://github.com/jfree/jfreepdf">https://github.com/jfree/jfreepdf</a>
 */
module org.jfree.pdf {
    requires java.base;
    requires java.desktop;
    requires java.logging;
    exports org.jfree.pdf;
}
