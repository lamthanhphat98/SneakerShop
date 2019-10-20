<?xml version="1.0" encoding="UTF-8"?>


<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="UTF-8"/>
    <xsl:template match="/">
        <html>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="shoese">
        <xsl:for-each select="shoes">
            <xsl:variable name="picture" select="Picture"/>
            <xsl:variable name="price" select="Price"/>
            <xsl:variable name="sizeName" select="SizeName"/>
            <xsl:variable name="productId" select="ProductId"/>
            <xsl:variable name="href">MainController?action=deleteCart</xsl:variable>          
            <xsl:variable name="quantity" select="Quantity"/>
            <tr>
                <td>
                    <img width="150px" height="150px" src=""
                         alt="Thumbnail">
                        <xsl:attribute name="src">
                            <xsl:value-of select="Picture"/>
 
                        </xsl:attribute>
                    </img> 
                    <b>                    
                        <xsl:value-of select="ProductName"/>
                    </b>
                </td>
                <td>
                    <a href="" class="minus">
                        <xsl:attribute name="href">MainController?action=minus&amp;shoesId=<xsl:value-of select="CartId"/></xsl:attribute>   
                    </a> 
                    <a href="" class="plus">
                        <xsl:attribute name="href">MainController?action=plus&amp;shoesId=<xsl:value-of select="CartId"/></xsl:attribute>   
                    </a>
                    <input type="text" readonly="true"  class="txtfield">
                        <xsl:attribute name="value">
                            <xsl:value-of select="Quantity"/>
                        </xsl:attribute>
                    </input>
                </td>
                <td>
                    <input type="text" readonly="true"  class="txtfield">
                        <xsl:attribute name="value">
                            <xsl:value-of select="SizeName"/>
                        </xsl:attribute>
                    </input>
                </td>
                <td class="last">
                    <div class="product_price">
                        <xsl:value-of select="Price*Quantity"/>
                    </div>
                </td>
                <td>
                    <a href="">
                        <xsl:attribute name="href">MainController?action=deleteCart&amp;shoesId=<xsl:value-of select="CartId"/>&amp;sizeName=<xsl:value-of select="SizeName"/></xsl:attribute>                        
                        <img width="25px" height="25px" src="images/delete.png" alt=""/>
                    </a>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
