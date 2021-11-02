package com.hst.scrapper.modules.scrap.domain.repo

import com.hst.scrapper.modules.scrap.domain.vo.WebMetadata

/**
 * @author dlgusrb0808@gmail.com
 */
interface WebMetadataReader {

    fun read(url: String): WebMetadata

}