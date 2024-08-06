@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;

    public void addInventory(Long productId, Long locationId, int quantity) {
        Inventory inventory = new Inventory();
        inventory.setProduct(new Product(productId));
        inventory.setLocation(new Location(locationId));
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    public void updateInventory(Long inventoryId, int quantity) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    public int checkStockLevels(Long productId) {
        List<Inventory> inventoryList = inventoryRepository.findByProductId(productId);
        return inventoryList.stream().mapToInt(Inventory::getQuantity).sum();
    }

    public void scheduleCycleCount(Long locationId, LocalDateTime scheduledDate) {
        CycleCount cycleCount = new CycleCount();
        cycleCount.setLocation(new Location(locationId));
        cycleCount.setScheduledDate(scheduledDate);
        cycleCount.setStatus("Scheduled");
        cycleCountRepository.save(cycleCount);
    }

    public void performCycleCount(Long cycleCountId, int actualQuantity) {
        CycleCount cycleCount = cycleCountRepository.findById(cycleCountId)
                .orElseThrow(() -> new RuntimeException("Cycle count not found"));
        cycleCount.setCompletedDate(LocalDateTime.now());
        cycleCount.setStatus("Completed");
        cycleCountRepository.save(cycleCount);

        Inventory inventory = inventoryRepository.findByLocationId(cycleCount.getLocation().getLocationId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(actualQuantity);
        inventoryRepository.save(inventory);
    }
}
