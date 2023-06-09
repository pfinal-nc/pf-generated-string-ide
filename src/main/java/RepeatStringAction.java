import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

import java.util.regex.Pattern;

public class RepeatStringAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here #*10
        //获取当前编辑器对象
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        //获取选择的数据模型
        SelectionModel selectionModel = editor.getSelectionModel();
        //获取当前选择的文本
        String selectedText = selectionModel.getSelectedText();

        assert selectedText != null;
        // System.out.println(replacePattern(selectedText));
        Runnable writeAction = () -> {
            // 根据空格分割成单词数组，遍历首字母大写拼接
            editor.getDocument().replaceString(selectionModel.getSelectionStart(), selectionModel.getSelectionEnd(), replacePattern(selectedText));
        };
        WriteCommandAction.runWriteCommandAction(editor.getProject(), writeAction);
    }

    public static String replacePattern(String str) {
        String[] parts = str.split(Pattern.quote("*"));
        String character = parts[0];
        int count = Integer.parseInt(parts[1]);

        StringBuilder sb = new StringBuilder();
        sb.ensureCapacity(count * character.length());  // 检查并增加容量

        // 对特殊字符进行转义
        // 对特殊字符进行转义
        character = character.replace("\\\\", "\\");

        for (int i = 0; i < count; i++) {
            sb.append(character);
        }

        // 对特殊字符进行转义
        String replacedCharacter = character.replace("\\", "\\\\");

        return str.replace(replacedCharacter + "*" + count, sb.toString());
    }
}
