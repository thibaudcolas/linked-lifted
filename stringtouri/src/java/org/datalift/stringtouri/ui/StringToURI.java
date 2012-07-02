/*
 * Copyright / Copr. LIRMM
 * Contributor(s) : T. Colas
 *
 * Contact: thibaud.colas@etud.univ-montp2.fr
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software. You can use,
 * modify and/or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty and the software's author, the holder of the
 * economic rights, and the successive licensors have only limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading, using, modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean that it is complicated to manipulate, and that also
 * therefore means that it is reserved for developers and experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and, more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

package org.datalift.stringtouri.ui;

import java.net.URI;
import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.datalift.fwk.Configuration;
import org.datalift.fwk.log.Logger;
import org.datalift.fwk.project.*;
import org.datalift.fwk.project.Source;
import org.datalift.fwk.project.Source.SourceType;


/*
 * A {@link ProjectModule project module} that replaces RDF object fields
 * from a {@link RdfFileSource RDF file source} by URIs to RDF entities.
 *
 * @author tcolas
 */
@Path(StringToURI.MODULE_NAME)
public class StringToURI extends BaseConverterModule
{
    //-------------------------------------------------------------------------
    // Constants
    //-------------------------------------------------------------------------

    /** The module name. */
    public static final String MODULE_NAME = "stringtouri";

    //-------------------------------------------------------------------------
    // Class members
    //-------------------------------------------------------------------------

    private final static Logger log = Logger.getLogger();

    //-------------------------------------------------------------------------
    // Constructors
    //-------------------------------------------------------------------------

    /**
     * Creates a new UIModule instance.
     */
    public StringToURI() {
        super(MODULE_NAME, 1000000000, SourceType.RdfFileSource);
    }

    //-------------------------------------------------------------------------
    // Web services
    //-------------------------------------------------------------------------

    @GET
    public Response getIndexPage(@QueryParam("project") URI projectId) {
        // Retrieve project.
        Project proj = this.getProject(projectId);
        // Display conversion configuration page with given args.
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("it", proj);
        args.put("converter", this);
        return Response.ok(this.newViewable("/converter.vm", args))
                .build();
    }
}
