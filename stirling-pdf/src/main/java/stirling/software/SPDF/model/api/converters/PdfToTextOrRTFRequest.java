package stirling.software.SPDF.model.api.converters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import stirling.software.common.model.api.PDFFile;

@Data
@EqualsAndHashCode(callSuper = true)
public class PdfToTextOrRTFRequest extends PDFFile {

    @Schema(
            description = "The output Text or RTF format",
            requiredMode = Schema.RequiredMode.REQUIRED,
            allowableValues = {"rtf", "txt"})
    private String outputFormat;
}
