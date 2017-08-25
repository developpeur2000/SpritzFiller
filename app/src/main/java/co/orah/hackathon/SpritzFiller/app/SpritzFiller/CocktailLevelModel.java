package co.orah.hackathon.SpritzFiller.app.SpritzFiller;

import com.vuforia.samples.SampleApplication.utils.MeshObject;

import java.nio.Buffer;

/**
 * Created by videostitch on 25-Aug-17.
 */

public class CocktailLevelModel extends MeshObject {

    int indicesNumber;
    int verticesNumber;

    double vertices[];

    // 4 triangles per side, so 12 indices per side
    short indices[];

    //double texCoords[];

    //double normals[];


    public CocktailLevelModel(float glassRadius, CocktailRecipe recipe)
    {
        int nbIngredients = recipe.getIngredients().size();

        //one square per ingredient = 4 vertices with 3 coordinates each
        vertices = new double[nbIngredients * 4 * 3];
        //one square = 2 triangles = 2 * 3 indices
        indices = new short[nbIngredients * 6];
        //texCoords = new double[?];
        //normals = new double[?];
        prepareData(glassRadius, recipe);

        mVertBuff = fillBuffer(vertices);
        //mTexCoordBuff = fillBuffer(texCoords);
        //mNormBuff = fillBuffer(normals);
        mIndBuff = fillBuffer(indices);
    }

    private Buffer mVertBuff;
    private Buffer mTexCoordBuff;
    private Buffer mNormBuff;
    private Buffer mIndBuff;


    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType)
    {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = mVertBuff;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = mTexCoordBuff;
                break;
            case BUFFER_TYPE_NORMALS:
                result = mNormBuff;
                break;
            case BUFFER_TYPE_INDICES:
                result = mIndBuff;
            default:
                break;

        }

        return result;
    }


    void prepareData(float glassRadius, CocktailRecipe recipe)
    {
        float squareBaseSize = 1.2f * glassRadius;
        float squareHeight = 0.0f;

        int curIndexVertices = 0;
        int curIndexIndices = 0;
        for (CocktailIngredient ingredient: recipe.getIngredients()) {
            squareHeight += ingredient.getProportion();
            //top left
            vertices[curIndexVertices + 0] = -squareBaseSize;
            vertices[curIndexVertices + 1] = squareBaseSize;
            vertices[curIndexVertices + 2] = squareHeight;
            //bottom left
            vertices[curIndexVertices + 3] = -squareBaseSize;
            vertices[curIndexVertices + 4] = -squareBaseSize;
            vertices[curIndexVertices + 5] = squareHeight;
            //bottom right
            vertices[curIndexVertices + 6] = squareBaseSize;
            vertices[curIndexVertices + 7] = -squareBaseSize;
            vertices[curIndexVertices + 8] = squareHeight;
            //top right
            vertices[curIndexVertices + 9] = squareBaseSize;
            vertices[curIndexVertices + 10] = squareBaseSize;
            vertices[curIndexVertices + 11] = squareHeight;

            short curIndexVertex = (short)(curIndexVertices / 3);
            indices[curIndexIndices + 0] = curIndexVertex;
            indices[curIndexIndices + 1] = (short)(curIndexVertex + 1);
            indices[curIndexIndices + 2] = (short)(curIndexVertex + 2);
            indices[curIndexIndices + 3] = curIndexVertex;
            indices[curIndexIndices + 4] = (short)(curIndexVertex + 2);
            indices[curIndexIndices + 5] = (short)(curIndexVertex + 3);

            curIndexVertices += 12;
            curIndexIndices += 6;
        }

        verticesNumber = vertices.length / 3;
        indicesNumber = indices.length;
    }


    @Override
    public int getNumObjectVertex()
    {
        return verticesNumber;
    }


    @Override
    public int getNumObjectIndex()
    {
        return indicesNumber;
    }
}
