import exceptions.FileReadException;
import exceptions.InvalidFileFormatException;

private final String glossaryFile = "source.txt";

void main()
{
    var glossary = new Glossary(glossaryFile);

    try
    {
        glossary.loadVocabulary();
    }
    catch (InvalidFileFormatException | FileReadException ex)
    {
        System.err.println("Ошибка: " + ex.getMessage());
        return;
    }

    var translator = new Translator(glossary);
    try(var scanner = new Scanner(System.in, StandardCharsets.UTF_8))
    {
        IO.println("Введите текст для перевода (пустая строка завершит работу): ");

        while (scanner.hasNextLine())
        {
            var line = scanner.nextLine();

            if (line.isBlank())
            {
                break;
            }

            IO.println(translator.translate(line));
        }
    }
    catch (Exception ex)
    {
        IO.println("Необработанная ошибка: " + ex.getMessage());
    }
}
