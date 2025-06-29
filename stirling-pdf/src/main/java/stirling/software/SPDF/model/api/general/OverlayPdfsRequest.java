package stirling.software.SPDF.model.api.general;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;
import stirling.software.common.model.api.PDFFile;

@Data
@EqualsAndHashCode(callSuper = true)
public class OverlayPdfsRequest extends PDFFile {

    @Schema(
            description =
                    "An array of PDF files to be used as overlays on the base PDF. The order in"
                            + " these files is applied based on the selected mode.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile[] overlayFiles;

    @Schema(
            description =
                    "The mode of overlaying: 'SequentialOverlay' for sequential application,"
                            + " 'InterleavedOverlay' for round-robin application, 'FixedRepeatOverlay'"
                            + " for fixed repetition based on provided counts",
            allowableValues = {"SequentialOverlay", "InterleavedOverlay", "FixedRepeatOverlay"},
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String overlayMode;

    @Schema(
            description =
                    "An array of integers specifying the number of times each corresponding overlay"
                            + " file should be applied in the 'FixedRepeatOverlay' mode. This should"
                            + " match the length of the overlayFiles array.",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int[] counts;

    @Schema(
            description = "Overlay position 0 is Foregound, 1 is Background",
            allowableValues = {"0", "1"},
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "number")
    private int overlayPosition;
}
