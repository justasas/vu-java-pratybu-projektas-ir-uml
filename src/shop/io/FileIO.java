package shop.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Iterator;
import shop.Item;
import shop.Shop;
import shop.exceptions.AddException;


import shop.exceptions.SetterException;
import shop.exceptions.ShopIOException;
import shop.gui.MainWindow;


public class FileIO {

	Shop shop;

	public FileIO(Shop shop) {
		this.shop = shop;
	}

    @SuppressWarnings("unchecked")
    public <T> void write(Collection<T> list, String fileName, Class<T> clazz) throws ShopIOException {
        @SuppressWarnings("rawtypes")
        Convertable converter = null;
        if (clazz.toString().indexOf("Client") > 0) {
                converter = (Convertable) new ClientConverter();
        }
        if (clazz.toString().indexOf("Order") > 0) {
        	converter = new OrderConverter(shop);
        }
        if (clazz.toString().indexOf("Item") > 0) {
                converter = (Convertable) new ItemConverter();
        }
        try {
            fileName = "data/" + fileName + ".dat";

            File file = new File(fileName);

            if (!file.exists()) {
                    file.createNewFile();
            }

            FileOutputStream fileOutStream = new FileOutputStream(
                            file.getAbsoluteFile());
            OutputStreamWriter outStream = new OutputStreamWriter(
                            fileOutStream, "UTF-8");
            BufferedWriter bWriter = new BufferedWriter(outStream);

            for (T obj : list) {
                    try {
                            bWriter.append(converter.getData(obj));
                            bWriter.newLine();
                    } catch (ShopIOException e) {
                            MainWindow.errorMessage(e.getMessage());
                    }
            }
            bWriter.close();
        } catch (IOException e) {
                MainWindow.errorMessage(e.getMessage());
        }

    }

	@SuppressWarnings("unchecked")
	public <T> void read(Collection<T> list, String fileName, Class<T> clazz) throws AddException {
		@SuppressWarnings("rawtypes")
		Convertable converter = null;
		if (clazz.toString().indexOf("Client") > 0) {
			converter = (Convertable) new ClientConverter();
		}
		if (clazz.toString().indexOf("Item") > 0) {
			converter = (Convertable) new ItemConverter();
		}
		if (clazz.toString().indexOf("Order") > 0) {
			converter = (Convertable) new OrderConverter(shop);
		}
                                
		try {
                    fileName = "data/" + fileName + ".dat";

                    File file = new File(fileName);

                    FileInputStream fileInStream = new FileInputStream(
                                    file.getAbsoluteFile());
                    InputStreamReader inputReader = new InputStreamReader(fileInStream,
                                    "UTF-8");
                    BufferedReader bReader = new BufferedReader(inputReader);

                    while (bReader.ready()) {
                            String info = bReader.readLine();
                            if (!shop.add((T) converter.readData(info))) {
                                    MainWindow.errorMessage("Warnning: Dupiclated values "
                                                    + info);
                            }
                    }
                    bReader.close();

		} catch (IOException e) {
			MainWindow.errorMessage(e.getMessage());
		} catch (ShopIOException e) {
			MainWindow.errorMessage(e.getMessage());
		} catch (NumberFormatException e) {
			MainWindow.errorMessage(e.getMessage());
		} catch (SetterException e) {
			MainWindow.errorMessage(e.getMessage());
		}
	}

	public void saveLinks(String fileName) {
		Item temp;

		try {
			Iterator<Item> iter = shop.getItems().iterator();
			fileName = "data/" + fileName + ".dat";

			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fWriter);

			while (iter.hasNext()) {
				temp = iter.next();

				bWriter.write(temp.getID() + ";");
				bWriter.write(";");
			}

			bWriter.close();

		} catch (IOException e) {
			MainWindow.errorMessage(e.getMessage());
		}

	}

	public void link(String fileName) {
		String info;
		Item tempitem;
		//int index;
		int id;
		String subString;
		try {
			fileName = "data/" + fileName + ".dat";
			File file = new File(fileName);

			if (!file.exists()) {
				return;
			}

			FileReader fReader = new FileReader(file.getAbsoluteFile());
			BufferedReader bReader = new BufferedReader(fReader);


			while (bReader.ready()) {
				try {
					info = bReader.readLine();
					subString = info.substring(0, info.indexOf(";"));

					//tempitem = new item(Integer.parseInt(subString));

					//index = shop.getitems().indexOf(tempitem);
					tempitem = shop.getItemAt(Integer.parseInt(subString));

					info = info.substring(info.indexOf(";") + 1);
					subString = info.substring(0, info.indexOf(";"));
					

					info = info.substring(info.indexOf(";") + 1);
					subString = info.substring(0, info.indexOf(";"));
					if (!subString.isEmpty()) {
						id = Integer.parseInt(subString);
						//tempitem.setEmployee(shop.findEmployee(id));
					}
					info = info.substring(info.indexOf(";") + 1);
					info = info.substring(0, info.indexOf(";"));

					while (!info.isEmpty()) {

						if (info.contains(",")) {
							subString = info.substring(0, info.indexOf(","));
							info = info.substring(info.indexOf(",") + 1);
						} else {
							subString = info;
							info = "";
						}
						id = Integer.parseInt(subString);
					}
				} catch (NumberFormatException e) {
					MainWindow.errorMessage(e.getMessage());
				}
			}
			bReader.close();
		} catch (IOException e) {
			MainWindow.errorMessage(e.getMessage());
		}
	}
}