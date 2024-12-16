<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
    
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4" page-height="29.7cm" page-width="21cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="12pt" font-family="Arial" line-height="1.5">
                        <fo:block font-size="14pt" font-weight="bold" margin-bottom="12pt">Datos de la Mascota</fo:block>
                        <fo:block>Nombre: <xsl:value-of select="mascota/nombre"/></fo:block>
                        <fo:block>Raza: <xsl:value-of select="mascota/raza"/></fo:block>
                        <fo:block>Edad: <xsl:value-of select="mascota/edad"/></fo:block>
                        <fo:block>Peso: <xsl:value-of select="mascota/peso"/></fo:block>
                        
                        <fo:block font-size="14pt" font-weight="bold" margin-top="20pt" margin-bottom="12pt">Datos del Cliente</fo:block>
                        <fo:block>Nombre: <xsl:value-of select="mascota/cliente/nombre"/></fo:block>
                        <fo:block>Apellidos: <xsl:value-of select="mascota/cliente/apellido1"/> <xsl:value-of select="mascota/cliente/apellido2"/></fo:block>
                        <fo:block>Dirección: <xsl:value-of select="mascota/cliente/direccion"/></fo:block>
                        <fo:block>Teléfono: <xsl:value-of select="mascota/cliente/telefono"/></fo:block>

                        <fo:block font-size="14pt" font-weight="bold" margin-top="20pt" margin-bottom="12pt">Medicamentos</fo:block>
                        <fo:block>
                            <xsl:for-each select="mascota/medicamentos/medicamento">
                                <fo:block>Medicamento: <xsl:value-of select="nombre"/> - Dosis: <xsl:value-of select="dosis"/></fo:block>
                            </xsl:for-each>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
