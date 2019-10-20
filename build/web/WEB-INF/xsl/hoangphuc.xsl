<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns="http://sneakershop.vn"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                version="1.0">
    <xsl:output method="xml"/>
    
    <xsl:variable name="domain" select="'https://hoang-phuc.com'"></xsl:variable>
    <xsl:template match="text()"></xsl:template>
    <xsl:template match="/">
        <xsl:element name="products">
            <xsl:apply-templates></xsl:apply-templates>
        </xsl:element>
    </xsl:template>
    <xsl:template match="//div[@class='product-block']">
        <xsl:element name="product">
            <xsl:element name="name">
                <xsl:value-of select="//h3//a"></xsl:value-of>
            </xsl:element>
            <xsl:element name="price">
                <xsl:variable name="price" select="//div[@class='box-pro-prices'][1]"></xsl:variable>
                <xsl:value-of select="translate($price, translate($price, '0123456789', ''), '')"></xsl:value-of>
            </xsl:element>
            <xsl:element name="picture">
                <xsl:variable name="image" select="//a[@class='product_image']//img//@src"></xsl:variable>

                <xsl:value-of select="concat($domain, substring-after($image, '..'))"></xsl:value-of>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
