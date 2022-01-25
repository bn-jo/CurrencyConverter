
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {


    public static void main(String[] args) throws IOException {

        Boolean running = true;

        do {

            HashMap<Integer, String> currencyCodes = new HashMap<Integer, String>();
            //add Currency Converter code
            currencyCodes.put(1, "USD");
            currencyCodes.put(2, "CAD");
            currencyCodes.put(3, "EUR");
            currencyCodes.put(4, "HKD");
            currencyCodes.put(5, "INR");
            currencyCodes.put(6, "BTC");
            currencyCodes.put(7, "CHF");
            currencyCodes.put(8, "BRL");
            currencyCodes.put(9, "ILS");
            currencyCodes.put(10, "RON");
            currencyCodes.put(11, "SEK");
            currencyCodes.put(12, "BGN");
            currencyCodes.put(13, "AUD");
            currencyCodes.put(14, "GBP");
            currencyCodes.put(15, "JPY");

            int from, to;
            double amount;

            Scanner sc = new Scanner(System.in);

            System.out.println();
            System.out.println("\t\t\tWelcome To Space Currency Converter !");
            System.out.println();
            System.out.println("Convert From?");


            System.out.println("1 : USD (United States Dollar) \t2 : CAD (Canadian Dollar)  " +
                    "\t3 : EUR (Euro)  \n4 : HKD (Hong Kong Dollar)  \t5 : INR (Indian Rupee)" +
                    "\n6 - BTC (Bitcoin) \t7 : CHF (Swiss Franc)  \t8 : BRL (Brazilian Real)  " +
                    "\n9 : ILS (Israeli New Sheqel)   \t10 : RON (Romanian Leu)" +
                    "\n11 - SEK (Swedish Krona) 12 : BGN (Bulgarian Lev) \t13 : AUD (Australian Dollar)  \n14 : GBP (British Pound Sterling) " +
                    "\t15 : JPY (Japanese Yen)");

            from = sc.nextInt();

            while (from < 1 || from > 15) {
                System.out.println("Please select a valid currency 1-15");


                System.out.println("1 : USD (United States Dollar) \t2 : CAD (Canadian Dollar)  " +
                        "\t3 : EUR (Euro)  \n4 : HKD (Hong Kong Dollar)  \t5 : INR (Indian Rupee)" +
                        "\n6 - BTC (Bitcoin) \t7 : CHF (Swiss Franc)  \t8 : BRL (Brazilian Real)  " +
                        "\n9 : ILS (Israeli New Sheqel)   \t10 : RON (Romanian Leu)" +
                        "\n11 - SEK (Swedish Krona) 12 : BGN (Bulgarian Lev) \t13 : AUD (Australian Dollar)  \n14 : GBP (British Pound Sterling) " +
                        "\t15 : JPY (Japanese Yen)");

                from = sc.nextInt();

            }


            System.out.println("Convert To?");

            System.out.println("1 : USD (United States Dollar) \t2 : CAD (Canadian Dollar)  " +
                    "\t3 : EUR (Euro)  \n4 : HKD (Hong Kong Dollar)  \t5 : INR (Indian Rupee)" +
                    "\n6 - BTC (Bitcoin) \t7 : CHF (Swiss Franc)  \t8 : BRL (Brazilian Real)  " +
                    "\n9 : ILS (Israeli New Sheqel)   \t10 : RON (Romanian Leu)" +
                    "\n11 - SEK (Swedish Krona) 12 : BGN (Bulgarian Lev) \t13 : AUD (Australian Dollar)  \n14 : GBP (British Pound Sterling) " +
                    "\t15 : JPY (Japanese Yen)");


            to = sc.nextInt();
            while (to< 1 || to> 15) {
                System.out.println("Please select a valid currency 1-15");


                System.out.println("1 : USD (United States Dollar) \t2 : CAD (Canadian Dollar)  " +
                        "\t3 : EUR (Euro)  \n4 : HKD (Hong Kong Dollar)  \t5 : INR (Indian Rupee)" +
                        "\n6 - BTC (Bitcoin) \t7 : CHF (Swiss Franc)  \t8 : BRL (Brazilian Real)  " +
                        "\n9 : ILS (Israeli New Sheqel)   \t10 : RON (Romanian Leu)" +
                        "\n11 - SEK (Swedish Krona) \t12 : BGN (Bulgarian Lev) \t13 : AUD (Australian Dollar)  \n14 : GBP (British Pound Sterling) " +
                        "\t15 : JPY (Japanese Yen)");


            }

            String fromCode,toCode;
            fromCode = currencyCodes.get(from);
            toCode = currencyCodes.get(to);

            System.out.println("Amount you wish to convert: ");
            amount = sc.nextFloat();
            sendHttpRequest(toCode, fromCode, amount);

            System.out.println();
            System.out.println("Make another conversion ?");
            System.out.println();
            System.out.println("1 - Yes  \t Any other Number  - No (Quit) " );

            if (sc.nextInt() != 1 ){
                running = false;
            }

             } while (running);
            System.out.println("Thank you for using Space currency converter");

              }
             private static void sendHttpRequest (String fromCode, String toCode,double amount) throws IOException {
            DecimalFormat f = new DecimalFormat("00.00");
            String GET_URL =
                    "http://api.exchangeratesapi.io/v1/latest?access_key=dd5aa89814d8a63d876d51d2fbfaa453&format=1" +
                            toCode + "&symbols=" + fromCode;

            URL url = new URL(GET_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                //SUCCESS
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JSONObject obj = new JSONObject(response.toString());
                Double exchangeRate = obj.getJSONObject("rates").getDouble(fromCode);

                System.out.println();
                System.out.println(exchangeRate); //keep for debug

                System.out.println();
                System.out.println((f.format(amount) + fromCode) + " = " + ( f.format(amount/exchangeRate) + toCode));

            } else {
                System.out.println("get request failed !");
            }
        }

    }
