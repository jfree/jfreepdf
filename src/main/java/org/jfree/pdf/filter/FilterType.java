/* =====================================================================
 * JFreePDF : a fast, light-weight PDF library for the Java(tm) platform
 * =====================================================================
 * 
 * (C)opyright 2013-2020, by Object Refinery Limited.  All rights reserved.
 *
 * Project Info:  http://www.object-refinery.com/orsonpdf/index.html
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 * 
 * If you do not wish to be bound by the terms of the GPL, an alternative
 * commercial license can be purchased.  For details, please see visit the
 * Orson PDF home page:
 * 
 * http://www.object-refinery.com/orsonpdf/index.html
 * 
 */

package org.jfree.pdf.filter;

import org.jfree.pdf.util.Args;

/**
 * Filter types for PDF streams.
 */
public enum FilterType {
    
    /** ASCII85 encoding. */
    ASCII85("/ASCII85Decode"),
    
    /** Flate encoding. */
    FLATE("/FlateDecode");
    
    private String decode;
    
    /**
     * Creates a new filter type.
     * 
     * @param decode  the decode value. 
     */
    FilterType(String decode) {
        Args.nullNotPermitted(decode, "decode");
        this.decode = decode;
    }
    
    /**
     * Returns the decode value that can be written in the PDF output for
     * this filter type.
     * 
     * @return The decode value (never {@code null}). 
     */
    public String getDecode() {
        return this.decode;
    }
}
