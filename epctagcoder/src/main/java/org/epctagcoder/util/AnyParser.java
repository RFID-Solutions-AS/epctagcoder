package org.epctagcoder.util;

import org.epctagcoder.option.GSRN.partitionTable.GSRNPartitionTableList;
import org.epctagcoder.parse.CPI.ParseCPI;
import org.epctagcoder.parse.GDTI.ParseGDTI;
import org.epctagcoder.parse.GIAI.ParseGIAI;
import org.epctagcoder.parse.GRAI.ParseGRAI;
import org.epctagcoder.parse.GSRN.ParseGSRN;
import org.epctagcoder.parse.GSRNP.ParseGSRNP;
import org.epctagcoder.parse.SGLN.ParseSGLN;
import org.epctagcoder.parse.SGTIN.ParseSGTIN;
import org.epctagcoder.parse.SSCC.ParseSSCC;
import org.epctagcoder.result.Base;
import org.epctagcoder.result.GSRN;

public class AnyParser {
    public static String parseEPCToPureIdentity(String epc) throws Exception {
        String header = Converter.hexToBin(epc).substring(0, 8);

        /* Headers
             CPI:   00111100 or 00111101
             GDTI:  00101100 or 00111110
             GIAI:  00110100 or 00111000
             GRAI:  00110011 or 00110111
             GSRN:  00101101
             GSRNP: 00101110
             SGLN:  00110010 or 00111001
             SGTIN: 00110000 or 00110110
             SSCC:  00110001
         */

        String pureIdentity = "unknown header";

        switch (header) {
            case "00111100":
            case "00111101":
                pureIdentity = ParseCPI.Builder().withRFIDTag(epc).build().getCPI().getEpcPureIdentityURI();
                break;
            case "00101100":
            case "00111110":
                pureIdentity = ParseGDTI.Builder().withRFIDTag(epc).build().getGDTI().getEpcPureIdentityURI();
                break;
            case "00110100":
            case "00111000":
                pureIdentity = ParseGIAI.Builder().withRFIDTag(epc).build().getGIAI().getEpcPureIdentityURI();
                break;
            case "00110011":
            case "00110111":
                pureIdentity = ParseGRAI.Builder().withRFIDTag(epc).build().getGRAI().getEpcPureIdentityURI();
                break;
            case "00101101":
                pureIdentity = ParseGSRN.Builder().withRFIDTag(epc).build().getGSRN().getEpcPureIdentityURI();
                break;
            case "00101110":
                pureIdentity = ParseGSRNP.Builder().withRFIDTag(epc).build().getGSRNP().getEpcPureIdentityURI();
                break;
            case "00110010":
            case "00111001":
                pureIdentity = ParseSGLN.Builder().withRFIDTag(epc).build().getSGLN().getEpcPureIdentityURI();
                break;
            case "00110000":
            case "00110110":
                pureIdentity = ParseSGTIN.Builder().withRFIDTag(epc).build().getSGTIN().getEpcPureIdentityURI();
                break;
            case "00110001":
                pureIdentity = ParseSSCC.Builder().withRFIDTag(epc).build().getSSCC().getEpcPureIdentityURI();
                break;
        }

        return pureIdentity;
    }

    public static void main(String[] args) {

    }
}
