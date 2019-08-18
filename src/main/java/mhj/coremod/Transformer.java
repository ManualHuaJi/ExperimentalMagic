package mhj.coremod;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.omg.PortableInterceptor.INACTIVE;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

public class Transformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("thaumcraft/api/research/theorycraft/ResearchTableData".equals(transformedName)) {
            ClassReader cr = new ClassReader(basicClass);
            ClassNode cn = new ClassNode(ASM5);
            cr.accept(cn, 0);

            for (MethodNode mn : cn.methods) {
                if (!"addTotal".equals(mn.name)) {
                    continue;
                }
                mn.instructions.clear();
                mn.instructions.add(new InsnNode(Opcodes.LDC));
                mn.instructions.add(new InsnNode(Opcodes.ILOAD));
                mn.instructions.add(new MethodInsnNode(Opcodes.ACC_PUBLIC, "Lmhj.expmm.research.ResearchTableData", "addTotal", "OBJECT,INT", false));
                mn.instructions.add(new InsnNode(Opcodes.RETURN));
                ClassWriter classWriter = new ClassWriter(2);
                cn.accept(classWriter);
                return classWriter.toByteArray();
            }
        }
        return basicClass;
    }
}
