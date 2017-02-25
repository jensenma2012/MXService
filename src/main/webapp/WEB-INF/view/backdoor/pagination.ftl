<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
<#if (page.pageCount > 1)>
	<div class="pagination">
		<#if (page.pageNumber == 1)>
			<span class="firstPage">&nbsp;</span>
		<#else>
			<a title="首页" class="firstPage" id="onePage" href="javascript: $.pageSkip(1);">&nbsp;</a>
		</#if>

		<#if (page.pageNumber > 1)>
			<a title="上一页" class="previousPage" href="javascript: $.pageSkip(${page.pageNumber}-1);">&nbsp;</a>
		<#else>
			<span class="previousPage">&nbsp;</span>
		</#if>

		<#if (page.pageNumber < page.pageCount)>
			<a title="下一页" class="nextPage" href="javascript: $.pageSkip(${page.pageNumber}+1);">&nbsp;</a>
		<#else>
			<span class="nextPage">&nbsp;</span>
		</#if>

		<#if (page.pageNumber == page.pageCount)>
			<span class="lastPage">&nbsp;</span>
		<#else>
			<a title="末尾" class="lastPage" href="javascript: $.pageSkip(${page.pageCount});">&nbsp;</a>
		</#if>
		
		<span class="pageSkip">
			共${page.pageCount}页  到第<input id="pageNumber" name="pageNumber" value=${page.pageNumber} maxlength="9" onpaste="return false;" />页<button type="submit">&nbsp;</button>
		</span>
	</div>
</#if>