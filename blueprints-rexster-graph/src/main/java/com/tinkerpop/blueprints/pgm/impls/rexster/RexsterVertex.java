package com.tinkerpop.blueprints.pgm.impls.rexster;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.MultiIterable;
import com.tinkerpop.blueprints.pgm.impls.StringFactory;
import com.tinkerpop.blueprints.pgm.impls.rexster.util.RestHelper;
import com.tinkerpop.blueprints.pgm.impls.rexster.util.RexsterEdgeSequence;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class RexsterVertex extends RexsterElement implements Vertex {

    public RexsterVertex(final JSONObject rawVertex, final RexsterGraph graph) {
        super(rawVertex, graph);
    }


    public Iterable<Edge> getOutEdges(final String... labels) {
        if (labels.length == 0)
            return new RexsterEdgeSequence(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.getId()) + RexsterTokens.SLASH_OUTE, this.graph);

        else if (labels.length == 1) {
            return new RexsterEdgeSequence(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.getId()) + RexsterTokens.SLASH_OUTE + RexsterTokens.QUESTION + RexsterTokens._LABEL_EQUALS + RestHelper.encode(labels[0]), this.graph);
        } else {

            final List<Iterable<Edge>> edges = new ArrayList<Iterable<Edge>>();
            for (final String label : labels) {
                edges.add(new RexsterEdgeSequence(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.getId()) + RexsterTokens.SLASH_OUTE + RexsterTokens.QUESTION + RexsterTokens._LABEL_EQUALS + RestHelper.encode(label), this.graph));
            }
            return new MultiIterable<Edge>(edges);
        }
    }

    public Iterable<Edge> getInEdges(final String... labels) {
        if (labels.length == 0)
            return new RexsterEdgeSequence(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.getId()) + RexsterTokens.SLASH_INE, this.graph);

        else if (labels.length == 1) {
            return new RexsterEdgeSequence(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.getId()) + RexsterTokens.SLASH_INE + RexsterTokens.QUESTION + RexsterTokens._LABEL_EQUALS + RestHelper.encode(labels[0]), this.graph);
        } else {

            final List<Iterable<Edge>> edges = new ArrayList<Iterable<Edge>>();
            for (final String label : labels) {
                edges.add(new RexsterEdgeSequence(this.graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + RestHelper.encode(this.getId()) + RexsterTokens.SLASH_INE + RexsterTokens.QUESTION + RexsterTokens._LABEL_EQUALS + RestHelper.encode(label), this.graph));
            }
            return new MultiIterable<Edge>(edges);
        }
    }

    public String toString() {
        return StringFactory.vertexString(this);
    }

    public JSONObject getRawVertex() {
        return RestHelper.getResultObject(graph.getGraphURI() + RexsterTokens.SLASH_VERTICES_SLASH + this.getId());
    }

}
