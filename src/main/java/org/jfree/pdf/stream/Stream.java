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

package org.jfree.pdf.stream;

import org.jfree.pdf.filter.Filter;
import org.jfree.pdf.util.Args;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.pdf.dictionary.Dictionary;
import org.jfree.pdf.internal.PDFObject;
import org.jfree.pdf.util.PDFUtils;

/**
 * A {@code Stream} is a {@link PDFObject} that has a {@link Dictionary} 
 * and a byte stream.  
 */
public abstract class Stream extends PDFObject {
    
    /** Filters (if any) to apply to the stream data. */
    private final List<Filter> filters;

    /**
     * Creates a new stream.
     * 
     * @param number  the PDF object number.
     */
    Stream(int number) {
        super(number);
        this.filters = new ArrayList<>();
    }

    /**
     * Adds a filter to the stream.
     * 
     * @param f  the filter ({@code null} not permitted).
     * 
     * @see #removeFilters() 
     */
    public void addFilter(Filter f) {
        Args.nullNotPermitted(f, "f");
        this.filters.add(f);    
    }
    
    /**
     * Removes any filters that were previously added.
     * 
     * @see #addFilter(org.jfree.pdf.filter.Filter) 
     */
    public void removeFilters() {
        this.filters.clear();
    }
    
    /**
     * Returns the PDF bytes for this stream object, with all current filters
     * applied.
     * 
     * @return The PDF bytes for this stream object.
     * 
     * @throws IOException  if there is a problem writing to the byte array.
     */
    @Override
    public byte[] getObjectBytes() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] streamData = getRawStreamData();
        for (Filter f: this.filters) {
            streamData = f.encode(streamData);
        }
        Dictionary dictionary = createDictionary(streamData.length);
        baos.write(dictionary.toPDFBytes());
        baos.write(PDFUtils.toBytes("stream\n"));
        baos.write(streamData);
        baos.write(PDFUtils.toBytes("endstream\n"));
        return baos.toByteArray();
    }

    /**
     * Creates the dictionary for this stream object.  The dictionary will
     * be populated with the stream length and the decode values for any
     * filters that are currently applied.
     * 
     * @param streamLength  the stream length.
     * 
     * @return The dictionary. 
     */
    protected Dictionary createDictionary(int streamLength) {
        Dictionary dictionary = new Dictionary();
        dictionary.put("/Length", streamLength);
        if (!this.filters.isEmpty()) {
            String[] decodes = new String[this.filters.size()];
            int count = this.filters.size();
            for (int i = 0; i < count; i++) {
                Filter f = this.filters.get(count - i - 1);
                decodes[i] = f.getFilterType().getDecode();
            }
            dictionary.put("/Filter", decodes);
        }
        return dictionary;
    }
    
    /**
     * Returns the raw data for the stream.
     * 
     * @return The raw data for the stream. 
     */
    public abstract byte[] getRawStreamData();

}
