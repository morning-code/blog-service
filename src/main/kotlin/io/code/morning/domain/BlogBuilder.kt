package io.code.morning.domain

import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension
import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.ext.toc.TocExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.pdf.converter.PdfConverterExtension
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.data.MutableDataSet

import io.code.morning.infrastructure.dynamodb.BlogDto
import org.springframework.stereotype.Component

@Component
class BlogBuilder {

  fun build(entity: BlogEntity): BlogDto {
    entity.let {
      return if (it.id != null) {
        BlogDto(
            id = it.id?.id,
            category = it.category,
            title = it.title,
            detail = buildHtmlFromMarkDown(it.detail)
        )
      } else {
        BlogDto(
            category = it.category,
            title = it.title,
            detail = buildHtmlFromMarkDown(it.detail)
        )
      }
    }
  }

  fun buildHtmlFromMarkDown(markDown: String?): String? {
    if (markDown.isNullOrEmpty()) return null

    // markdown settings
    val options = MutableDataSet()
    options.set(Parser.EXTENSIONS,
        listOf(
            AnchorLinkExtension.create(),
            StrikethroughExtension.create(),
            TablesExtension.create(),
            TocExtension.create()
        )
    )

    val parser = Parser.builder(options).build()
    val renderer = HtmlRenderer.builder(options).build()

    // convert markdown -> html
    val document: Node = parser.parse(markDown)

    return renderer.render(document)
  }
}