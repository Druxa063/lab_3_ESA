<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/lessonList">
        <html>
            <body>
                <a href="/groups">Groups</a><br/>
                <a href="/teachers">Teachers</a><br/>
                <table align="center">
                    <thead>
                        <th>Name</th>
                        <th>Start time</th>
                        <th>Group</th>
                        <th>Teacher</th>
                    </thead>
                    <tbody>
                        <xsl:for-each select="lessons">
                            <tr>
                                <td><xsl:value-of select="name"/></td>
                                <td><xsl:value-of select="startTime"/></td>
                                <td><xsl:value-of select="group/name"/></td>
                                <td><xsl:value-of select="teacher/firstName"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>