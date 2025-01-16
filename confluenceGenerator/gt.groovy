package confluenceGenerator

import groovy.xml.MarkupBuilder

class ReportBuilder {
    String type
    StringWriter writer
    MarkupBuilder builder

    LinkedHashMap<String, Object> bindMap = [
        "base": WrapperBaseConfl
    ]

    ReportBuilder(String type = "base") {
        this.type = type
        writer = new StringWriter()
        builder = new MarkupBuilder(writer)
    }

    def section(Closure content) {
        builder.'ac:structured-macro'('ac:name': 'section') {
            builder.'ac:rich-text-body' {
                content.delegate = this
                content.call()
            }
        }
        return this
    }

    def name(String block, Map attributes = [:]) {
        builder.'ac:name' {
            writer.append(block)
        }
    }

    def paragraph(String block, Map attributes = [:]) {
        builder.'ac:paragraph' {
            writer.append(block)
        }
    }

    def img(String block, Map attributes = [:]) {
        builder.'ac:image' {
            writer.append(block)
        }
    }

    def addBlock(String content) {
        return content
    }

    def build() {
        // Используем toString() для получения строки
        return bindMap[type].getXML(writer.toString())
    }
}

class WrapperBaseConfl {
    static String getXML(String storage) {
        // Преобразуем GString в строку
        return """<ac:layout>
            ${storage.toString()}  // Явное преобразование к строке
        </ac:layout>"""
    }
}

// Пример использования

def report = new ReportBuilder()

def pageContent = report
    .section {
        name(
            report.addBlock("<h1>Title</h1>")
        )
        paragraph(
            report.addBlock("<div>...</div>"),
            ["ac:name": "toc", "ac:macro": "..."]
        )
    }
    .section {
        name(
            report.addBlock("<h1>Another Title</h1>"),
            ["ac:example": "toc", "ac:macro": "..."]
        )
        paragraph(
            report.addBlock("<div>Content here</div>")
        )
        img(
            report.addBlock("<img src='image.jpg'/>")
        )
    }
    .build()

println(pageContent)
