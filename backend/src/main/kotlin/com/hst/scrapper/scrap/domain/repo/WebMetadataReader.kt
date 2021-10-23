package com.hst.scrapper.scrap.domain.repo

import com.hst.scrapper.scrap.domain.vo.WebMetadata

/**
 * @author dlgusrb0808@gmail.com
 */
interface WebMetadataReader {

    fun read(url: String): WebMetadata

}