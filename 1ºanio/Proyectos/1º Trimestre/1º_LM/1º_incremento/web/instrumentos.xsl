<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8" />
                <title>Catálogo de Instrumentos</title>
            </head>
            <body>
                <h1>Catálogo de Instrumentos</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Marca</th>
                        <th>Categoría</th>
                        <th>Precio €</th>
                        <th>Stock</th>
                    </tr>
                    <xsl:for-each select="tienda/catalogo/instrumento">
                        <tr>
                            <td>
                                <xsl:value-of select="@id" />
                            </td>
                            <td>
                                <xsl:value-of select="nombre" />
                            </td>
                            <td>
                                <xsl:value-of select="marca" />
                            </td>
                            <td>
                                <xsl:value-of select="@categoria" />
                            </td>
                            <td>
                                <xsl:value-of select="precio" />
                            </td>
                            <td>
                                <xsl:value-of select="stock" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

                <h1>Proveedores</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>País</th>
                        <th>Contacto</th>
                    </tr>
                    <xsl:for-each select="tienda/proveedores/proveedor">
                        <tr>
                            <td>
                                <xsl:value-of select="@id" />
                            </td>
                            <td>
                                <xsl:value-of select="nombre" />
                            </td>
                            <td>
                                <xsl:value-of select="pais" />
                            </td>
                            <td>
                                <xsl:value-of select="contacto" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>