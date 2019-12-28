<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/employeeList">
        <html>
            <body>
                <table align="center">
                    <thead>
                        <th>EmpNO</th>
                        <th>Ename</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="employees">
                            <tr>
                                <td><xsl:value-of select="empno"/></td>
                                <td><xsl:value-of select="ename"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>