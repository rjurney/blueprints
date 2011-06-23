package com.tinkerpop.blueprints.pgm.impls.rexster;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.StringFactory;
import com.tinkerpop.blueprints.pgm.impls.rexster.util.RestHelper;
import org.codehaus.jettison.json.JSONObject;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class RexsterEdge extends RexsterElement implements Edge {

    private final String label;
    private final Object outVertex;
    private final Object inVertex;


    public RexsterEdge(final JSONObject rawEdge, final RexsterGraph graph) {
        super(rawEdge, graph);
        this.label = rawEdge.optString(RexsterTokens._LABEL);
        this.outVertex = rawEdge.opt(RexsterTokens._OUTV);
        this.inVertex = rawEdge.opt(RexsterTokens._INV);
    }

    public Vertex getOutVertex() {
        return new RexsterVertex(RestHelper.getResultObject(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.outVertex)), this.graph);
    }

    public Vertex getInVertex() {
        return new RexsterVertex(RestHelper.getResultObject(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.inVertex)), this.graph);
    }

    public String getLabel() {
        return this.label;
    }

    public String toString() {
        return StringFactory.E + StringFactory.L_BRACKET + this.getId() + StringFactory.R_BRACKET + StringFactory.L_BRACKET + this.outVertex + StringFactory.DASH + this.getLabel() + StringFactory.ARROW + this.inVertex + StringFactory.R_BRACKET;
    }

    public JSONObject getRawEdge() {
        return RestHelper.getResultObject(graph.getGraphURI() + RexsterTokens.SLASH_EDGES_SLASH + this.getId());
    }

}
