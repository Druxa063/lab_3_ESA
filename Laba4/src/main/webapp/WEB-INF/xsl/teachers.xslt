<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/teacherList">
        <html>
            <body>
                <a href="/groups">Groups</a><br/>
                <a href="/lessons">Lessons</a><br/>
                <table align="center">
                    <thead>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>Position</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="teachers">
                            <tr>
                                <td><xsl:value-of select="firstName"/></td>
                                <td><xsl:value-of select="lastName"/></td>
                                <td><xsl:value-of select="Position"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>