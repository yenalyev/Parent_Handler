import INPUT.Excel;
import INPUT.InputFileWorker;
import org.apache.poi.ss.usermodel.Row;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

class InputFileWorkerTest {
    InputFileWorker inputFileWorkerObhect = new InputFileWorker();
    @Test
    void getChildIdOfVirtualProducts() {
        String test = "id[:===:]1340089456[:&&&:]check[:===:]yes[:&&&:]title[:===:]ILLUSION Wheels[:&&&:]description[:===:]ILLUSION Black with Ball Cut Machined Spokes Wheels by Konig®. {short description 10}[:&&&:]order[:===:]10[:&&&:]images[:===:]path[:==:]images/konig/wheels/konig-illusion-black-ball-cut-machined-spokes.jpg[:&&:]alt[:==:]KONIG® - ILLUSION Black with Ball Cut Machined Spokes[:&&:]order[:==:]1[:&&&:]groups[:===:]id[:==:]868743258[:&&:]classname[:==:]FRONT WHEEL SIZE:[:&&:]classtext[:==:][:&&:]order[:==:]0[:&&:]invisible[:==:]N[:&&:]options[:==:]id[:=:]3251987690[:&:]productid[:=:]465440[:&:]optname[:=:]15\" DIAMETER (15\" x 6.5\") [+38 Offset, 4x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]1[:os:]id[:=:]3251987692[:&:]productid[:=:]465441[:&:]optname[:=:]15\" DIAMETER (15\" x 6.5\") [+38 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]2[:os:]id[:=:]3251987694[:&:]productid[:=:]465442[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 4x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]3[:os:]id[:=:]3251987696[:&:]productid[:=:]465443[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 5x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]4[:os:]id[:=:]2836983132[:&:]productid[:=:]465444[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 5x110 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]5[:os:]id[:=:]3251987698[:&:]productid[:=:]465445[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]5[:os:]id[:=:]2836983134[:&:]productid[:=:]465446[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+35 Offset, 5x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]7[:os:]id[:=:]3251987700[:&:]productid[:=:]465447[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+45 Offset, 5x112 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]6[:os:]id[:=:]3251987702[:&:]productid[:=:]465448[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+35 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]7[:os:]id[:=:]3251987704[:&:]productid[:=:]465449[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+45 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]8[:gs:]id[:==:]868743259[:&&:]classname[:==:]REAR WHEEL SIZE:[:&&:]classtext[:==:][:&&:]order[:==:]1[:&&:]invisible[:==:]N[:&&:]options[:==:]id[:=:]3251987691[:&:]productid[:=:]465440[:&:]optname[:=:]15\" DIAMETER (15\" x 6.5\") [+38 Offset, 4x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]1[:os:]id[:=:]3251987693[:&:]productid[:=:]465441[:&:]optname[:=:]15\" DIAMETER (15\" x 6.5\") [+38 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]2[:os:]id[:=:]3251987695[:&:]productid[:=:]465442[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 4x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]3[:os:]id[:=:]3251987697[:&:]productid[:=:]465443[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 5x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]4[:os:]id[:=:]2836983155[:&:]productid[:=:]465444[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 5x110 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]5[:os:]id[:=:]3251987699[:&:]productid[:=:]465445[:&:]optname[:=:]17\" DIAMETER (17\" x 7\") [+40 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]5[:os:]id[:=:]2836983157[:&:]productid[:=:]465446[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+35 Offset, 5x100 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]7[:os:]id[:=:]3251987701[:&:]productid[:=:]465447[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+45 Offset, 5x112 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]6[:os:]id[:=:]3251987703[:&:]productid[:=:]465448[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+35 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]7[:os:]id[:=:]3251987705[:&:]productid[:=:]465449[:&:]optname[:=:]18\" DIAMETER (18\" x 8\") [+45 Offset, 5x114.3 Bolt Pattern, 73.1mm Hub][:&:]order[:=:]8";
        for (String s: inputFileWorkerObhect.getChildIdOfVirtualProducts(test)) {
            System.out.println(s);
        }
    }



    @Test
    void maxKeyInMap(){
        TreeMap<Row, String> excelContent = new TreeMap<>();

        {
            try {
                excelContent = Excel.readInMapFromExcelByColumnTitle("C:\\Users\\Maxim\\Desktop\\test1.xls", "Virtual Products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(inputFileWorkerObhect.maxKeyInMap(excelContent));


    }

    @Test
    void getParentChildInfo(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result = inputFileWorkerObhect.getParentChildInfo("C:\\Users\\Maxim\\Desktop\\test1.xls", "Virtual Products", "SKU");
        for (ArrayList<String> s: result) {
            System.out.println(s);
        }
    }

    @Test
    void getParentChildInfoWithValidationByChildID(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        result = inputFileWorkerObhect.getParentChildInfoWithValidationByChildID("C:\\Users\\Maxim\\Desktop\\test1.xls", "Virtual Products", "Product ID", "Product ID");
        for (ArrayList<String> s: result) {
            System.out.println(s);
        }
    }
}