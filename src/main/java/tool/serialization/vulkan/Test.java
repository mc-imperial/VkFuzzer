package tool.serialization.vulkan;

/**
 * Created by constantinos on 29/03/2016.
 */
public class Test {
    public Test() {
//        VkInstanceCreateInfoConfig instanceCreateInfoConfig = new VkInstanceCreateInfoConfig();
//        VkApplicationInfoConfig applicationInfoConfig = new VkApplicationInfoConfig();
//        instanceCreateInfoConfig.setApplicationInfo("hello");
//        applicationInfoConfig.setApiVersion("hi");
//
//        VulkanGlobalState configs = new VulkanGlobalState();
//        configs.addConfig(instanceCreateInfoConfig);
//        configs.addConfig(applicationInfoConfig);
//
//        Config[] collection = new Config[2];
//        collection[0] = instanceCreateInfoConfig;
//        collection[1] = applicationInfoConfig;
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.addMixIn(Config.class, PolymorphicVulkanConfigMixIn.class);
//
//        StringWriter writer = new StringWriter();
//
//        try {
//            mapper.writeValue(writer, configs);
//            System.out.println(writer.toString());
//            VulkanGlobalState deserialized = mapper.readValue(writer.toString(), VulkanGlobalState.class);
//            for (Config c : deserialized.getConfigs()) {
//                System.out.println(c);
//            }
//            Config[] deserialized =
//            for (Config c : deserialized) {
//                if (c instanceof  VkApplicationInfoConfig) {
//                    System.out.println(((VkApplicationInfoConfig) c).getApiVersion());
//                } else if (c instanceof  VkInstanceCreateInfoConfig) {
//                    System.out.println(((VkInstanceCreateInfoConfig) c).getApplicationInfo());
//                }
//
//
//
//            }

//            VkApplicationInfoConfig[] stuff =
//                    Arrays.stream(deserialized.getConfigs().toArray()).filter(x -> x instanceof VkApplicationInfoConfig).toArray(VkApplicationInfoConfig[]::new);
//
//            System.out.println(stuff.length);
//
//            for (VkApplicationInfoConfig c : stuff) {
//                System.out.println(c.getApiVersion());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
//        cfg.setClassForTemplateLoading(Fuzzer.class.getClass(), "/templates/vulkan");
//        cfg.setDefaultEncoding("UTF-8");
//
//        try {
//            Template temp1 = cfg.getTemplate("instance/VkApplicationInfo.ftl");
//            Template temp2 = cfg.getTemplate("instance/VkCreateInstance.ftl");
//
//            Map<String, Config> root = new HashMap<>();
//            StringWriter writer = new StringWriter();
//            temp1.process(root, writer);
//
//            VkApplicationInfoConfig lala = new VkApplicationInfoConfig();
//            root.put("config", lala);
//            lala.setVariableName("hello");
//            lala.setApiVersion("sfs");
//            lala.setApplicationName("asdasf");
//            lala.setEngineName("sdfsdf");
//            lala.setNext("sdfs");
//            lala.setType("asfwe");
//
//            VkCreateInstanceConfig lolo = new VkCreateInstanceConfig();
//            lolo.setResult("sdfsd");
//            lolo.setAlloc("sfsdf");
//            lolo.setInstanceCreateInfo("sfdfsd");
//            lolo.setInstanceName("sdfsdf");
//
//            StringWriter writer2 = new StringWriter();
//            temp1.process(root, writer2);
//
//            root.put("config", lolo);
//            temp2.process(root, writer2);
//            System.out.println(writer2.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
    }
}
